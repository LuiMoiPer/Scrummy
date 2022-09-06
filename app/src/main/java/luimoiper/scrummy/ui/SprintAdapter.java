package luimoiper.scrummy.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

import luimoiper.scrummy.R;
import luimoiper.scrummy.db.Sprint;
import luimoiper.scrummy.models.SprintModel;
import luimoiper.scrummy.utils.Generator;

public class SprintAdapter
        extends RecyclerView.Adapter<SprintAdapter.ViewHolder>
        implements ListAdapter<Sprint>
{
    private List<Sprint> sprintModels;
    private ListItemListener listItemListener;

    public SprintAdapter(List<Sprint> sprintModels, ListItemListener listItemListener) {
        this.sprintModels = sprintModels;
        this.listItemListener = listItemListener;
    }

    @Override
    public Sprint getItem(int position) {
        if (position >= 0 && position < sprintModels.size()) {
            return sprintModels.get(position);
        }
        return null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sprint_list_item, parent, false);
        return new ViewHolder(view, listItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sprint sprint = sprintModels.get(position);
        holder.title.setText(sprint.name);
        holder.startDate.setText(String.valueOf(sprint.startDate));
        holder.endDate.setText(String.valueOf(sprint.endDate));
        holder.totalPoints.setText(String.valueOf(Generator.random.nextInt()));
    }

    @Override
    public int getItemCount() {
        return sprintModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView startDate;
        final TextView endDate;
        final TextView totalPoints;

        public ViewHolder(@NonNull View itemView, ListItemListener listItemListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            startDate = itemView.findViewById(R.id.startDate);
            endDate = itemView.findViewById(R.id.endDate);
            totalPoints = itemView.findViewById(R.id.storyPoints);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listItemListener != null && position != RecyclerView.NO_POSITION) {
                    listItemListener.onItemClick(position);
                }
            });
        }
    }
}
