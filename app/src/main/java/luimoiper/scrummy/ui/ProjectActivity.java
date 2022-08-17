package luimoiper.scrummy.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import luimoiper.scrummy.R;
import luimoiper.scrummy.models.ProjectModel;
import luimoiper.scrummy.utils.Generator;

public class ProjectActivity extends AppCompatActivity {
    private ProjectModel projectModel;
    private FragmentManager fragmentManager;
    private ListFragment backlogItemsFragment;
    private ListFragment sprintsFragment;

    private TextView title;
    private TextView description;

    private FragmentContainerView fragmentContainer;
    private Button backlogButton;
    private Button sprintsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        backlogItemsFragment = new ListFragment(
                new TaskAdapter(Generator.makeTaskModels(20), this::onBacklogItemClick)
        );

        setContentView(R.layout.project_activity);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        fragmentContainer = findViewById(R.id.fragmentContainer);
        backlogButton = findViewById(R.id.backlogButton);
        sprintsButton = findViewById(R.id.sprintsButton);

        Intent intent = getIntent();
        projectModel = intent.getParcelableExtra("ProjectModel");

        setViewContent();
    }

    private void setViewContent() {
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(fragmentContainer.getId(), backlogItemsFragment)
                .commit();

        if (projectModel != null) {
            title.setText(projectModel.getTitle());
            description.setText(projectModel.getDescription());
        } else {
            title.setText("Project model is NULL");
            description.setText("Project model is NULL");
        }

        setButtonActions();
    }

    private void setButtonActions() {
        Context context = this;
        backlogButton.setOnClickListener(view -> {
            Toast.makeText(context, "Backlog button", Toast.LENGTH_SHORT).show();
        });

        sprintsButton.setOnClickListener(view -> {
            Toast.makeText(context, "Sprint button", Toast.LENGTH_SHORT).show();
        });
    }

    private void onBacklogItemClick(int position) {
        Toast.makeText(this, "Backlog item at position " + position, Toast.LENGTH_SHORT).show();
    }

    private void onSprintItemClick(int position) {
        // do stuff
    }
}
