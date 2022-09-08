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

    private TextView title;
    private TextView description;

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        projectDao = Access.getScrumDatabase(this).projectDao();
        sprintDao = Access.getScrumDatabase(this).sprintDao();

        setTitle(ACTIVITY_TITLE);
        setContentView(R.layout.project_activity);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ProjectActivityAdapter(this));

        Intent intent = getIntent();
        int projectUid = intent.getIntExtra("ProjectUid", -1);
        project = projectDao.get(projectUid);

        setViewContent();
    }

    private void setViewContent() {
        if (project != null) {
            title.setText(project.name);
            description.setText(project.description);
        } else {
            title.setText("Project model is NULL");
            description.setText("Project model is NULL");
        }

        setTabActions();
    }

    private void setTabActions() {
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager, this::configureTabs).attach();
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

    public class ProjectActivityAdapter extends FragmentStateAdapter {
        public ProjectActivityAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment;

            if (position == 0) {
                fragment = new BacklogListFragment(project.uid);
            } else {
                fragment = new SprintListFragment();
            }

            return fragment;
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
