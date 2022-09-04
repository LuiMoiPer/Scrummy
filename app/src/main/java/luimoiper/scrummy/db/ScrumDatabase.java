package luimoiper.scrummy.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Project.class, Sprint.class, Task.class}, version = 1)
public abstract class ScrumDatabase extends RoomDatabase {
    public abstract ProjectDao projectDao();
    public abstract SprintDao sprintDao();
    public abstract TaskDao taskDao();
}
