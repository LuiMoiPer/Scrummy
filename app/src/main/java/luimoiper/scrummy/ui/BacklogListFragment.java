package luimoiper.scrummy.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import luimoiper.scrummy.db.Access;
import luimoiper.scrummy.db.Task;
import luimoiper.scrummy.db.TaskDao;

public class BacklogListFragment extends AbstractListFragment {
    int projectUid;

    public BacklogListFragment(int projectUid) {
        this.projectUid = projectUid;
        adapter = new TaskAdapter(getTasks(), this::onItemClick);
        fabListener = this::onFabClick;
    }

    private void onFabClick(View view) {
        Intent intent = new Intent(getContext(), AddBacklogItemActivity.class);
        intent.putExtra("ProjectUid", projectUid);
        startActivity(intent);
    }

    private void onItemClick(int position) {
        Intent intent = new Intent(getContext(), TaskActivity.class);
        TaskAdapter taskAdapter = (TaskAdapter) adapter;
        // intent.putExtra("TaskModel",  taskAdapter.getItem(position));
        startActivity(intent);
    }

    private List<Task> getTasks() {
        TaskDao taskDao = Access.getScrumDatabase(getContext()).taskDao();
        return taskDao.getProjectBacklogTasks(projectUid);
    }
}
