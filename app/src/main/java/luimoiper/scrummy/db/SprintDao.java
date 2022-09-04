package luimoiper.scrummy.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SprintDao {
    @Query("SELECT * FROM sprint")
    List<Sprint> getAll();

    @Insert
    void insertAll(Sprint... sprints);

    @Delete
    void delete(Sprint sprint);
}
