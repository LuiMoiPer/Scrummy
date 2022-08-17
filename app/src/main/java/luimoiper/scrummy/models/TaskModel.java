package luimoiper.scrummy.models;

public class TaskModel {
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
}
