package luimoiper.scrummy.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TaskModel implements Parcelable {
    private String title;
    private String description;
    private int points;
    private String status;

    public TaskModel(String title, String description, int points, String status) {
        this.title = title;
        this.description = description;
        this.points = points;
        this.status = status;
    }

    protected TaskModel(Parcel in) {
        title = in.readString();
        description = in.readString();
        points = in.readInt();
        status = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static final Creator<TaskModel> CREATOR = new Creator<TaskModel>() {
        @Override
        public TaskModel createFromParcel(Parcel in) {
            return new TaskModel(in);
        }

        @Override
        public TaskModel[] newArray(int size) {
            return new TaskModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(points);
        dest.writeString(status);
    }
}
