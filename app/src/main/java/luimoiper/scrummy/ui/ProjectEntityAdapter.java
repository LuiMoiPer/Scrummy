package luimoiper.scrummy.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import luimoiper.scrummy.R;
import luimoiper.scrummy.db.Project;

public class ProjectEntityAdapter extends RecyclerView.Adapter<ProjectEntityAdapter.ViewHolder>
        implements ListAdapter<Project> {

    private Project[] projectModels;
    private ListItemListener listItemListener;

    public ProjectEntityAdapter(List<Project> projectModels, ListItemListener listItemListener) {
        this.projectModels = projectModels.toArray(new Project[0]);
        this.listItemListener = listItemListener;
    }

    @Override
    public Project getItem(int position) {
        if (position >= 0 && position < projectModels.length) {
            return projectModels[position];
        }
        return null;
    }

    @NonNull
    @Override
    public ProjectEntityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.project_list_item, parent, false);
        return new ProjectEntityAdapter.ViewHolder(view, listItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectEntityAdapter.ViewHolder holder, int position) {
        Project projectEntity = projectModels[position];
        holder.title.setText(projectEntity.name);
        holder.description.setText(projectEntity.description);
    }

    @Override
    public int getItemCount() {
        return projectModels.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView title;
        final TextView description;

        public ViewHolder(View itemView, ListItemListener listItemListener) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listItemListener != null && position != RecyclerView.NO_POSITION) {
                    listItemListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
