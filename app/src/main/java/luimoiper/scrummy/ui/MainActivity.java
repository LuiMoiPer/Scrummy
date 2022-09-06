package luimoiper.scrummy.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import luimoiper.scrummy.R;
import luimoiper.scrummy.db.Access;
import luimoiper.scrummy.db.Project;
import luimoiper.scrummy.db.ProjectDao;

public class MainActivity extends AppCompatActivity implements ListItemListener {
    private static final String ACTIVITY_TITLE = "Scrummy";

    private ProjectDao projectDao;
    private ProjectEntityAdapter projectAdapter;
    private List<Project> projects;

    private FloatingActionButton fab;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(ACTIVITY_TITLE);
        setContentView(R.layout.main_activity);

        projectDao = Access.getScrumDatabase(getApplicationContext()).projectDao();
        projects = projectDao.getAll();

        recyclerView = findViewById(R.id.list);
        fab = findViewById(R.id.fab);

        setupViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        projects = projectDao.getAll();
        resetRecyclerView();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, ProjectActivity.class);
        intent.putExtra("ProjectUid", projects.get(position).uid);
        startActivity(intent);
    }

    private void resetRecyclerView() {
        projectAdapter = new ProjectEntityAdapter(projects, this);
        recyclerView.setAdapter(projectAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupViews() {
        resetRecyclerView();
        new ItemTouchHelper(projectTouchCallback).attachToRecyclerView(recyclerView);
        fab.setOnClickListener(v -> onFabClick());
    }

    private void onFabClick() {
        startActivity(new Intent(this, AddProjectActivity.class));
    }

    ItemTouchHelper.SimpleCallback projectTouchCallback = new ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
    ) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            Project removedProject = projects.remove(position);
            projectDao.delete(removedProject);
            projectAdapter.notifyItemRemoved(position);
        }
    };
}