package luimoiper.scrummy.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sprint {
    @PrimaryKey
    public int uid;

    public int parentProjectUid;
    public String name;
    public long startDate;
    public long endDate;
}
