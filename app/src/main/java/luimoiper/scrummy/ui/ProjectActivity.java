package luimoiper.scrummy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import luimoiper.scrummy.R;
import luimoiper.scrummy.db.Access;
import luimoiper.scrummy.db.Project;
import luimoiper.scrummy.db.ProjectDao;
import luimoiper.scrummy.db.SprintDao;
import luimoiper.scrummy.utils.Generator;

public class ProjectActivity extends FragmentActivity {
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
    private ViewPager2 viewPager;
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
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ProjectActivityAdapter(this));

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
        backlogItemsFragment = new ListFragment(
                backlogItemAdapter,
                v -> Toast.makeText(this, "Backlog item fab pressed", Toast.LENGTH_SHORT)
                        .show()
        );
        sprintsFragment = new ListFragment(
                sprintAdapter,
                v -> Toast.makeText(this, "Sprint item fab pressed", Toast.LENGTH_SHORT)
                        .show()
        );

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

        new TabLayoutMediator(tabLayout, viewPager, this::configureTabs).attach();

        TabLayout.TabView backlogTab = (TabLayout.TabView) viewGroup.getChildAt(0);
        backlogTab.setOnClickListener(view -> {
            replaceFragment(backlogItemsFragment);
        });

        TabLayout.TabView sprintsTab = (TabLayout.TabView) viewGroup.getChildAt(1);
        sprintsTab.setOnClickListener(view -> {
            replaceFragment(sprintsFragment);
        });
    }

    private void configureTabs(TabLayout.Tab tab, int position) {
        switch (position) {
            case 0:
                tab.setText("Backlog");
                break;

            case 1:
                tab.setText("Sprints");
                break;
        }
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

    public class ProjectActivityAdapter extends FragmentStateAdapter {
        public ProjectActivityAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("ProjectUid", project.uid);
            Fragment fragment;

            if (position == 0) {
                fragment = new BacklogListFragment();
            }
            else {
                fragment = new SprintListFragment();
            }

            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
