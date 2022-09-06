package luimoiper.scrummy.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BacklogTaskDao {
    @Query("SELECT * FROM backlogtask")
    List<BacklogTask> getAll();

    @Query("SELECT * FROM BacklogTask WHERE projectUid = :projectUid")
    List<BacklogTask> getBacklogTasks(int projectUid);

    @Insert
    void insertAll(BacklogTask... backlogTasks);

    @Delete
    void delete(BacklogTask backlogTask);
}
