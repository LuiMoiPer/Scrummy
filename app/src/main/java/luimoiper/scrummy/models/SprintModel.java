package luimoiper.scrummy.models;

import java.util.Date;

public class SprintModel {
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
}
