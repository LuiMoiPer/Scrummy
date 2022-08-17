package luimoiper.scrummy.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import luimoiper.scrummy.R;
import luimoiper.scrummy.models.SprintModel;

public class SprintAdapter extends RecyclerView.Adapter<SprintAdapter.ViewHolder> {
    private SprintModel[] sprintModels;
    private ListItemListener listItemListener;

    public SprintAdapter(SprintModel[] sprintModels, ListItemListener listItemListener) {
        this.sprintModels = sprintModels;
        this.listItemListener = listItemListener;
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
        SprintModel sprintModel = sprintModels[position];
        holder.title.setText(sprintModel.getTitle());
        holder.startDate.setText(sprintModel.getStartDate().toString());
        holder.endDate.setText(sprintModel.getEndDate().toString());
        holder.totalPoints.setText(String.valueOf(sprintModel.getTotalPoints()));
    }

    @Override
    public int getItemCount() {
        return sprintModels.length;
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
