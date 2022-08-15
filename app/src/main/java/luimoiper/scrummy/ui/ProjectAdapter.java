package luimoiper.scrummy.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import luimoiper.scrummy.R;
import luimoiper.scrummy.models.ProjectModel;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private ListItemListener listItemListener;
    private ProjectModel[] projectModels;

    public ProjectAdapter(ProjectModel[] projectModels, ListItemListener listItemListener) {
        this.projectModels = projectModels;
        this.listItemListener = listItemListener;
    }

    public ProjectModel getItem(int position) {
        if (position >= 0 && position < projectModels.length) {
            return projectModels[position];
        }
        return null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item, parent, false);
        return new ViewHolder(view, listItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProjectModel projectModel = projectModels[position];
        holder.getTitle().setText(projectModel.getTitle());
        holder.getDescription().setText(projectModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return projectModels.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;

        public ViewHolder(View view, ListItemListener listItemListener) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listItemListener != null && position != RecyclerView.NO_POSITION) {
                        listItemListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDescription() {
            return description;
        }
    }
}
