package luimoiper.scrummy.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = Project.class,
                        parentColumns = "uid",
                        childColumns = "projectUid",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Task.class,
                        parentColumns = "uid",
                        childColumns = "taskUid",
                        onDelete = ForeignKey.CASCADE
                )
        },
        primaryKeys = {"projectUid", "taskUid"},
        indices = {
                @Index(value = "projectUid"),
                @Index(value = "taskUid")
        }
)
public class BacklogTask {
    public int projectUid;
    public int taskUid;
    public int priority;
}
