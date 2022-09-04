package luimoiper.scrummy.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Project {
    @PrimaryKey
    public int uid;

    public String name;
    public String description;
}
