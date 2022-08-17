package luimoiper.scrummy.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import luimoiper.scrummy.R;
import luimoiper.scrummy.models.ProjectModel;

public class ProjectActivity extends AppCompatActivity {
    private ProjectModel projectModel;

    private TextView title;
    private TextView description;

    private Button backlogButton;
    private Button sprintsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_activity);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        backlogButton = findViewById(R.id.backlogButton);
        sprintsButton = findViewById(R.id.sprintsButton);

        Intent intent = getIntent();
        projectModel = intent.getParcelableExtra("ProjectModel");

        setViewContent();
    }

    private void setViewContent() {
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
}
