package gst.trainingcourse.final_mock.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


import gst.trainingcourse.final_mock.dao.PhotoDao;
import gst.trainingcourse.final_mock.models.ItemPhoto;

@Database(entities = {ItemPhoto.class}, version = 1,exportSchema = false)
public abstract class BluetoothDatabase extends RoomDatabase {

    private static BluetoothDatabase instance;


    public abstract PhotoDao getDaoPhoto();


    public static BluetoothDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BluetoothDatabase.class,
                    "bluetooth_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

}
