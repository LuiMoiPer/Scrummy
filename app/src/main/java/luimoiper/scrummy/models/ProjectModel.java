package luimoiper.scrummy.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ProjectModel implements Parcelable {
    private String title;
    private String description;

    public ProjectModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    protected ProjectModel(Parcel in) {
        title = in.readString();
        description = in.readString();
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

    public static final Creator<ProjectModel> CREATOR = new Creator<ProjectModel>() {
        @Override
        public ProjectModel createFromParcel(Parcel in) {
            return new ProjectModel(in);
        }

        @Override
        public ProjectModel[] newArray(int size) {
            return new ProjectModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
    }
}
