package luimoiper.scrummy.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import luimoiper.scrummy.models.TaskModel;
import luimoiper.scrummy.utils.Generator;

public class BacklogListFragment extends AbstractListFragment {
    public BacklogListFragment() {
        TaskModel[] tasks = Generator.makeTaskModels(Generator.random.nextInt(14) + 1);
        adapter = new TaskAdapter(tasks, this::onItemClick);
        fabListener = this::onFabClick;
    }

    private void onFabClick(View view) {
        Toast.makeText(getContext(), "Backlog fab pressed", Toast.LENGTH_SHORT).show();
    }

    private void onItemClick(int position) {
        Intent intent = new Intent(getContext(), TaskActivity.class);
        TaskAdapter taskAdapter = (TaskAdapter) adapter;
        intent.putExtra("TaskModel",  taskAdapter.getItem(position));
        startActivity(intent);
    }
}
