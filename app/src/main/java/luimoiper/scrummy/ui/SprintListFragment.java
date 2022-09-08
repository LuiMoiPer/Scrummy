package luimoiper.scrummy.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import luimoiper.scrummy.db.Sprint;

public class SprintListFragment extends AbstractListFragment {
    public SprintListFragment() {
        List<Sprint> sprints = new LinkedList<>();
        adapter = new SprintAdapter(sprints, this::onItemClick);
        fabListener = this::onFabClick;
    }

    private void onFabClick(View view) {
        Toast.makeText(getContext(), "Sprint fab pressed", Toast.LENGTH_SHORT).show();
    }

    private void onItemClick(int position) {
        Intent intent = new Intent(getContext(), TaskActivity.class);
        SprintAdapter sprintAdapter = (SprintAdapter) adapter;
        intent.putExtra("SprintUid", sprintAdapter.getItem(position).uid);
        startActivity(intent);
    }
}
