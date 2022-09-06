package luimoiper.scrummy.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import java.util.Map;

@Dao
public interface SprintDao {
    @Query("SELECT * FROM sprint")
    List<Sprint> getAll();

    @Query("SELECT * FROM sprint WHERE parentProjectUid = :projectUid")
    List<Sprint> getProjectSprints(int projectUid);

    @Insert
    void insertAll(Sprint... sprints);

    @Delete
    void delete(Sprint sprint);
}
