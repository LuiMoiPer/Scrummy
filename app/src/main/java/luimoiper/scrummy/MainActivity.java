package luimoiper.scrummy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import luimoiper.scrummy.models.ProjectModel;
import luimoiper.scrummy.ui.ProjectAdapter;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.list);
        ProjectAdapter projectAdapter = new ProjectAdapter(generateProjects(30));
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
}