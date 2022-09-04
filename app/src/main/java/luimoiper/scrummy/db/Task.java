package luimoiper.scrummy.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {
    @PrimaryKey
    public int uid;

    public String title;
    public String description;
    public String acceptanceCriteria;
    public int parentTaskId;
    public int pointValue;
    public int statusId;
}
