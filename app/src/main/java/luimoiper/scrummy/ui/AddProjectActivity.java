package luimoiper.scrummy.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;

import java.util.List;

import luimoiper.scrummy.R;
import luimoiper.scrummy.db.Access;
import luimoiper.scrummy.db.Project;
import luimoiper.scrummy.db.ProjectDao;

public class AddProjectActivity extends AppCompatActivity {
    private static final String ACTIVITY_TITLE = "Add Project";

    ProjectDao projectDao;

    EditText title;
    EditText description;
    Button addButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(ACTIVITY_TITLE);

        projectDao = Access.getScrumDatabase(this).projectDao();

        setContentView(R.layout.add_project_activity);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        addButton = findViewById(R.id.add_button);
        cancelButton = findViewById(R.id.cancel_button);

        setupViews();
    }

    private void setupViews() {
        addButton.setOnClickListener(v -> onAddButtonClick());
        cancelButton.setOnClickListener(v -> onCancelButtonClick());
    }

    private void onAddButtonClick() {
        Project project = new Project();
        project.name = title.getText().toString();
        project.description = description.getText().toString();

        projectDao.insertAll(project);
        finish();
    }

    private void onCancelButtonClick() {
        finish();
    }
}
