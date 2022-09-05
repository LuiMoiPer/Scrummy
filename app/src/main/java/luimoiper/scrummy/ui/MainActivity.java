package luimoiper.scrummy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import luimoiper.scrummy.R;
import luimoiper.scrummy.db.Access;
import luimoiper.scrummy.db.ProjectDao;

public class MainActivity extends AppCompatActivity implements ListItemListener {
    private static final String ACTIVITY_TITLE = "Scrummy";

    private ProjectDao projectDao;
    private ProjectEntityAdapter projectAdapter;

    private FloatingActionButton fab;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(ACTIVITY_TITLE);
        setContentView(R.layout.main_activity);

        projectDao = Access.getScrumDatabase(getApplicationContext()).projectDao();

        recyclerView = findViewById(R.id.list);
        fab = findViewById(R.id.fab);

        setupViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resetRecyclerView();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ProjectActivity.class);
        // intent.putExtra("ProjectModel", projectAdapter.getItem(position));
        startActivity(intent);
    }

    private void resetRecyclerView() {
        projectAdapter = new ProjectEntityAdapter(projectDao.getAll(), this);
        recyclerView.setAdapter(projectAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupViews() {
        resetRecyclerView();
        fab.setOnClickListener(v -> onFabClick());
    }

    private void onFabClick() {
        startActivity(new Intent(this, AddProjectActivity.class));
    }
}