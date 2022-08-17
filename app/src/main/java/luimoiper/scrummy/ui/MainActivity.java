package luimoiper.scrummy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import luimoiper.scrummy.R;
import luimoiper.scrummy.models.ProjectModel;
import luimoiper.scrummy.utils.Generator;

public class MainActivity extends AppCompatActivity implements ListItemListener {
    private RecyclerView recyclerView;
    private ProjectAdapter projectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        recyclerView = findViewById(R.id.list);
        projectAdapter = new ProjectAdapter(Generator.makeProjectModelArray(30), this);
        recyclerView.setAdapter(projectAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ProjectActivity.class);
        intent.putExtra("ProjectModel", projectAdapter.getItem(position));
        startActivity(intent);
    }
}