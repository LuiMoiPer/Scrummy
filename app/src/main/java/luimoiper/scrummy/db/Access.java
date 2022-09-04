package luimoiper.scrummy.db;

import android.content.Context;

import androidx.room.Room;

public class Access {
    static ScrumDatabase scrumDatabase = null;

    public static ScrumDatabase getScrumDatabase(Context context) {
        if (scrumDatabase == null) {
            scrumDatabase = Room.databaseBuilder(
                    context,
                    ScrumDatabase.class,
                    "ScrumDatabase")
                    .allowMainThreadQueries()
                    .build();
        }
        return scrumDatabase;
    }
}
