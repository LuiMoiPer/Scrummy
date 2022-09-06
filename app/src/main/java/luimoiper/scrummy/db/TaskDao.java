package luimoiper.scrummy.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT task.*"
            + " FROM backlogtask JOIN task "
            + " ON backlogTask.taskUid = task.uid"
            + " WHERE taskUid = :projectUid"
    )
    List<Task> getProjectBacklogTasks(int projectUid);

    @Query("SELECT * FROM task WHERE uid = :taskUid")
    Task getTask(int taskUid);

    @Query("SELECT * FROM task WHERE rowid = :rowId")
    Task getTaskFromRowId(int rowId);

    @Insert
    void insertAll(Task... tasks);

    @Insert
    long insert(Task task);

    @Delete
    void delete(Task task);
}
