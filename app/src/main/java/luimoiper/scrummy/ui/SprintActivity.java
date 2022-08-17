package luimoiper.scrummy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import luimoiper.scrummy.R;
import luimoiper.scrummy.models.SprintModel;
import luimoiper.scrummy.utils.Generator;

public class SprintActivity extends AppCompatActivity {
    private SprintModel sprintModel;
    private FragmentManager fragmentManager;

    private TextView title;
    private TextView startDate;
    private TextView endDate;
    private TextView totalPoints;

    private FragmentContainerView fragmentContainer;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sprint_activity);
        fragmentManager = getSupportFragmentManager();

        title = findViewById(R.id.title);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        totalPoints = findViewById(R.id.totalPoints);
        fragmentContainer = findViewById(R.id.fragmentContainer);

        Intent intent = getIntent();
        sprintModel = intent.getParcelableExtra("SprintModel");

        setViewContent();
        setFragment();
    }

    private void setViewContent() {
        if (sprintModel != null) {
            title.setText(sprintModel.getTitle());
            startDate.setText(sprintModel.getStartDate().toString());
            endDate.setText(sprintModel.getEndDate().toString());
            totalPoints.setText(String.valueOf(sprintModel.getTotalPoints()));
        }
        else {
            title.setText("Sprint model is NULL");
            startDate.setText("Sprint model is NULL");
            endDate.setText("Sprint model is NULL");
            totalPoints.setText("Sprint model is NULL");
        }
    }

    private void setFragment() {
        taskAdapter = new TaskAdapter(
                Generator.makeTaskModels(20), this::onSprintItemClick
        );
        ListFragment sprintItemsFragment = new ListFragment(taskAdapter);

        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(fragmentContainer.getId(), sprintItemsFragment)
                .commit();
    }

    private void onSprintItemClick(int position) {
        Toast.makeText(this, "Sprint item at position " + position, Toast.LENGTH_SHORT).show();
    }
}
