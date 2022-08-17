package luimoiper.scrummy.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class SprintModel implements Parcelable {
    private String title;
    private Date startDate;
    private Date endDate;
    private int totalPoints;

    public SprintModel(String title, Date startDate, Date endDate, int totalPoints) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPoints = totalPoints;
    }

    protected SprintModel(Parcel source) {
        title = source.readString();
        startDate = new Date(source.readLong());
        endDate = new Date(source.readLong());
        totalPoints = source.readInt();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public static final Creator<SprintModel> CREATOR = new Creator<SprintModel>() {
        @Override
        public SprintModel createFromParcel(Parcel source) {
            return new SprintModel(source);
        }

        @Override
        public SprintModel[] newArray(int size) {
            return new SprintModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeLong(startDate.getTime());
        parcel.writeLong(endDate.getTime());
        parcel.writeInt(totalPoints);
    }
}
