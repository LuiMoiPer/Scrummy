package luimoiper.scrummy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;

import luimoiper.scrummy.R;
import luimoiper.scrummy.db.Access;
import luimoiper.scrummy.db.Project;
import luimoiper.scrummy.db.ProjectDao;
import luimoiper.scrummy.db.SprintDao;
import luimoiper.scrummy.utils.Generator;

public class ProjectActivity extends AppCompatActivity {
    private static final String ACTIVITY_TITLE = "Project Details";

    private ProjectDao projectDao;
    private SprintDao sprintDao;

    private Project project;
    private FragmentManager fragmentManager;
    private ListFragment backlogItemsFragment;
    private ListFragment sprintsFragment;

    private TextView title;
    private TextView description;

    private FragmentContainerView fragmentContainer;
    private TaskAdapter backlogItemAdapter;
    private SprintAdapter sprintAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        projectDao = Access.getScrumDatabase(this).projectDao();
        sprintDao = Access.getScrumDatabase(this).sprintDao();

        setTitle(ACTIVITY_TITLE);
        setContentView(R.layout.project_activity);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        fragmentContainer = findViewById(R.id.fragmentContainer);

        Intent intent = getIntent();
        int projectUid = intent.getIntExtra("ProjectUid", -1);
        project = projectDao.get(projectUid);

        setViewContent();
    }

    private void setAdapters() {
        backlogItemAdapter = new TaskAdapter(
                Generator.makeTaskModels(20),
                this::onBacklogItemClick
        );
        sprintAdapter = new SprintAdapter(
                sprintDao.getProjectSprints(project.uid),
                this::onSprintClick
        );
    }

    private void setViewContent() {
        setAdapters();

        fragmentManager = getSupportFragmentManager();
        backlogItemsFragment = new ListFragment(backlogItemAdapter);
        sprintsFragment = new ListFragment(sprintAdapter);

        if (project != null) {
            title.setText(project.name);
            description.setText(project.description);
        } else {
            title.setText("Project model is NULL");
            description.setText("Project model is NULL");
        }

        replaceFragment(backlogItemsFragment);
        setTabActions();
    }

    private void setTabActions() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewGroup viewGroup = (ViewGroup) tabLayout.getChildAt(0);

        TabLayout.TabView backlogTab = (TabLayout.TabView) viewGroup.getChildAt(0);
        backlogTab.setOnClickListener(view -> {
            replaceFragment(backlogItemsFragment);
        });

        TabLayout.TabView sprintsTab = (TabLayout.TabView) viewGroup.getChildAt(1);
        sprintsTab.setOnClickListener(view -> {
            replaceFragment(sprintsFragment);
        });
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(fragmentContainer.getId(), fragment)
                .commit();
    }

    private void onBacklogItemClick(int position) {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra("TaskModel", backlogItemAdapter.getItem(position));
        startActivity(intent);
    }

    private void onSprintClick(int position) {
        Intent intent = new Intent(this, SprintActivity.class);
        intent.putExtra("SprintUid", sprintAdapter.getItem(position).uid);
        startActivity(intent);
    }
}
