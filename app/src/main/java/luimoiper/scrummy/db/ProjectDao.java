package luimoiper.scrummy.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProjectDao {
    @Query("SELECT * FROM project")
    List<Project> getAll();

    @Query("SELECT * FROM project WHERE project.uid = :uid")
    Project get(int uid);

    @Insert
    void insertAll(Project... projects);

    @Delete
    void delete(Project project);
}
