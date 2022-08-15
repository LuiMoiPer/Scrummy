package luimoiper.scrummy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import luimoiper.scrummy.models.ProjectModel;
import luimoiper.scrummy.ui.ListItemListener;
import luimoiper.scrummy.ui.ProjectAdapter;

public class MainActivity extends AppCompatActivity implements ListItemListener {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        recyclerView = findViewById(R.id.list);
        ProjectAdapter projectAdapter = new ProjectAdapter(generateProjects(30), this);
        recyclerView.setAdapter(projectAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    protected ProjectModel[] generateProjects(int count) {
        ProjectModel[] projectModels = new ProjectModel[count];
        for (int i = 0; i < projectModels.length; i++) {
            projectModels[i] = new ProjectModel("Title " + i, "Description " + i);
        }
        return projectModels;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ProjectActivity.class);
        startActivity(intent);
    }
}