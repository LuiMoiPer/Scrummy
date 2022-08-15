package luimoiper.scrummy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

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
    }

    private void setButtonActions() {
        backlogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sprintsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
