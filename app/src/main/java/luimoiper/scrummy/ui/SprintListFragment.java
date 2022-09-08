package luimoiper.scrummy.ui;

import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import luimoiper.scrummy.db.Sprint;

public class SprintListFragment extends AbstractListFragment {
    public SprintListFragment() {
        List<Sprint> sprints = new LinkedList<>();
        adapter = new SprintAdapter(sprints, null);
        fabListener = view -> {
            Toast.makeText(getContext(), "Sprint fab pressed", Toast.LENGTH_SHORT).show();
        };
    }
}
