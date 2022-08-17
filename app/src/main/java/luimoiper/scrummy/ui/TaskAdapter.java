package luimoiper.scrummy.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import luimoiper.scrummy.R;
import luimoiper.scrummy.models.TaskModel;

public class TaskAdapter
        extends RecyclerView.Adapter<TaskAdapter.ViewHolder>
        implements ListAdapter<TaskModel>
{
    private TaskModel[] taskModels;
    private ListItemListener listItemListener;

    public TaskAdapter(TaskModel[] taskModels, ListItemListener listItemListener) {
        this.taskModels = taskModels;
        this.listItemListener = listItemListener;
    }

    @Override
    public TaskModel getItem(int position) {
        if (position >= 0 && position < taskModels.length) {
            return taskModels[position];
        }
        return null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.task_list_item, parent, false);
        return new ViewHolder(view, listItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskModel taskModel = taskModels[position];
        holder.title.setText(taskModel.getTitle());
        holder.status.setText(taskModel.getStatus());
        holder.description.setText(taskModel.getDescription());
        holder.points.setText(String.valueOf(taskModel.getPoints()));
    }

    @Override
    public int getItemCount() {
        return taskModels.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView status;
        final TextView description;
        final TextView points;

        public ViewHolder(@NonNull View itemView, ListItemListener listItemListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            status = itemView.findViewById(R.id.status);
            description = itemView.findViewById(R.id.description);
            points = itemView.findViewById(R.id.storyPoints);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listItemListener != null && position != RecyclerView.NO_POSITION) {
                    listItemListener.onItemClick(position);
                }
            });
        }
    }
}
