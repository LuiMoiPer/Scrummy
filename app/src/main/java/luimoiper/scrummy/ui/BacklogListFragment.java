package luimoiper.scrummy.ui;

import android.widget.Toast;

import luimoiper.scrummy.models.TaskModel;
import luimoiper.scrummy.utils.Generator;

public class BacklogListFragment extends AbstractListFragment {
    public BacklogListFragment() {
        TaskModel[] tasks = Generator.makeTaskModels(Generator.random.nextInt(14) + 1);
        adapter = new TaskAdapter(tasks, null);
        fabListener = view -> {
            Toast.makeText(getContext(), "Backlog fab pressed", Toast.LENGTH_SHORT).show();
        };
    }
}
