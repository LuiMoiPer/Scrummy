package luimoiper.scrummy.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;

import java.util.List;

import luimoiper.scrummy.R;
import luimoiper.scrummy.db.Access;
import luimoiper.scrummy.db.BacklogTask;
import luimoiper.scrummy.db.BacklogTaskDao;
import luimoiper.scrummy.db.Project;
import luimoiper.scrummy.db.ProjectDao;
import luimoiper.scrummy.db.Task;
import luimoiper.scrummy.db.TaskDao;

public class AddBacklogItemActivity extends AppCompatActivity {
    private static final String ACTIVITY_TITLE = "Add Backlog Item";

    EditText title;
    EditText description;
    EditText criteria;
    Button addButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(ACTIVITY_TITLE);

        setContentView(R.layout.add_backlog_item_activity);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        criteria = findViewById(R.id.criteria);
        addButton = findViewById(R.id.add_button);
        cancelButton = findViewById(R.id.cancel_button);

        setupViews();
    }

    private void setupViews() {
        addButton.setOnClickListener(v -> onAddButtonClick());
        cancelButton.setOnClickListener(v -> onCancelButtonClick());
    }

    private void onAddButtonClick() {
        Toast.makeText(this, "Add backlog item", Toast.LENGTH_SHORT).show();
        insertTaskToDatabase();
        finish();
    }

    private void onCancelButtonClick() {
        finish();
    }

    private void insertTaskToDatabase() {
        int projectUid = getIntent().getIntExtra("ProjectUid", -1);
        if (projectUid >= 0) {
            Task task = new Task();
            task.title = title.getText().toString();
            task.description = description.getText().toString();
            task.acceptanceCriteria = criteria.getText().toString();
            task.parentTaskId = -1;

            TaskDao taskDao = Access.getScrumDatabase(this).taskDao();
            long taskRowId = taskDao.insert(task);

            BacklogTask backlogTask = new BacklogTask();
            backlogTask.taskUid = (int) taskRowId;
            backlogTask.projectUid = projectUid;
            BacklogTaskDao backlogTaskDao = Access.getScrumDatabase(this).backlogTaskDao();
            backlogTaskDao.insertAll(backlogTask);
        }
    }
}
