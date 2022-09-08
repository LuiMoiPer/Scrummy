package luimoiper.scrummy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import java.util.LinkedList;

import luimoiper.scrummy.R;
import luimoiper.scrummy.models.TaskModel;
import luimoiper.scrummy.utils.Generator;

public class TaskActivity extends AppCompatActivity {
    private static final String ACTIVITY_TITLE = "Task Details";

    private TaskModel taskModel;
    private FragmentManager fragmentManager;

    private TextView title;
    private TextView status;
    private TextView storyPoints;
    private TextView description;

    private FragmentContainerView fragmentContainer;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(ACTIVITY_TITLE);
        setContentView(R.layout.task_activity);
        fragmentManager = getSupportFragmentManager();

        title = findViewById(R.id.title);
        status = findViewById(R.id.status);
        storyPoints = findViewById(R.id.storyPoints);
        description = findViewById(R.id.description);
        fragmentContainer = findViewById(R.id.fragmentContainer);

        Intent intent = getIntent();
        if (intent.hasExtra("TaskModel")) {
            taskModel = intent.getParcelableExtra("TaskModel");
        }

        setViewContent();
        setFragment();
    }

    private void setViewContent() {
        if (taskModel != null) {
            title.setText(taskModel.getTitle());
            status.setText(taskModel.getStatus());
            storyPoints.setText(String.valueOf(taskModel.getPoints()));
            description.setText(taskModel.getDescription());
        }
        else {
            title.setText("Task model is NULL");
            status.setText("Task model is NULL");
            storyPoints.setText("Task model is NULL");
            description.setText("Task model is NULL");
        }
    }

    private void setFragment() {
        taskAdapter = new TaskAdapter(
                new LinkedList<>(),
                this::onSubtaskItemClick
        );
        ListFragment subtaskItemsFragment = new ListFragment(taskAdapter, null);

        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(fragmentContainer.getId(), subtaskItemsFragment)
                .commit();
    }

    private void onSubtaskItemClick(int position) {
        Toast.makeText(this, "Subtask item at position " + position, Toast.LENGTH_SHORT).show();
    }
}
