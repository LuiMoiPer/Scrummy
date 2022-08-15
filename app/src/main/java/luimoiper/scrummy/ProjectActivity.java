package luimoiper.scrummy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import luimoiper.scrummy.models.ProjectModel;

public class ProjectActivity extends AppCompatActivity {
    private ProjectModel projectModel;

    private TextView title;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_activity);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        Intent intent = getIntent();
        projectModel = intent.getParcelableExtra("ProjectModel");

        setViewContent();
    }

    private void setViewContent() {
        if (projectModel != null) {
            title.setText(projectModel.getTitle());
            description.setText(projectModel.getDescription());
        }
        else {
            title.setText("NULL");
            description.setText("NULL");
        }
    }
}
