package luimoiper.scrummy.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sprint {
    @PrimaryKey
    int uid;

    long startDate;
    long endDate;
}
