package luimoiper.scrummy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;

import luimoiper.scrummy.R;
import luimoiper.scrummy.models.ProjectModel;
import luimoiper.scrummy.utils.Generator;

public class ProjectActivity extends AppCompatActivity {
    private static final String ACTIVITY_TITLE = "Project Details";

    private ProjectModel projectModel;
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
        setTitle(ACTIVITY_TITLE);
        setAdapters();
        fragmentManager = getSupportFragmentManager();
        backlogItemsFragment = new ListFragment(backlogItemAdapter);
        sprintsFragment = new ListFragment(sprintAdapter);

        setContentView(R.layout.project_activity);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        fragmentContainer = findViewById(R.id.fragmentContainer);

        Intent intent = getIntent();
        projectModel = intent.getParcelableExtra("ProjectModel");

        setViewContent();
    }

    private void setAdapters() {
        backlogItemAdapter = new TaskAdapter(
                Generator.makeTaskModels(20),
                this::onBacklogItemClick
        );
        sprintAdapter = new SprintAdapter(
                Generator.makeSprintModels(6),
                this::onSprintClick
        );
    }

    private void setViewContent() {
        if (projectModel != null) {
            title.setText(projectModel.getTitle());
            description.setText(projectModel.getDescription());
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
        Toast.makeText(this, "Backlog item at position " + position, Toast.LENGTH_SHORT).show();
    }

    private void onSprintClick(int position) {
        Intent intent = new Intent(this, SprintActivity.class);
        intent.putExtra("SprintModel", sprintAdapter.getItem(position));
        startActivity(intent);
    }
}
