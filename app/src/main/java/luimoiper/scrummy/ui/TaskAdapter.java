package luimoiper.scrummy.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import luimoiper.scrummy.R;
import luimoiper.scrummy.db.Task;
import luimoiper.scrummy.models.TaskModel;
import luimoiper.scrummy.utils.Generator;

public class TaskAdapter
        extends RecyclerView.Adapter<TaskAdapter.ViewHolder>
        implements ListAdapter<Task>
{
    private List<Task> tasks;
    private ListItemListener listItemListener;

    public TaskAdapter(List<Task> tasks, ListItemListener listItemListener) {
        this.tasks = tasks;
        this.listItemListener = listItemListener;
    }

    @Override
    public Task getItem(int position) {
        if (position >= 0 && position < tasks.size()) {
            return tasks.get(position);
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
        Task task = tasks.get(position);
        holder.title.setText(task.title);
        holder.status.setText(Generator.makeCharSequence());
        holder.description.setText(task.description);
        holder.points.setText(String.valueOf(task.pointValue));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
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
