package com.rogernkosi.rainassessment.persistance;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rogernkosi.rainassessment.persistance.dao.LocationDao;
import com.rogernkosi.rainassessment.persistance.model.PinnedLocation;

@Database(entities = {PinnedLocation.class}, version = 1)
public abstract class LocationDatabase extends RoomDatabase {

    private static volatile LocationDatabase INSTANCE;

    public abstract LocationDao locationDao();

    private static final String DB_NAME = "PinnedLocation.db";

    public static LocationDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (LocationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LocationDatabase.class, DB_NAME).build();
                }
            }
        }
        return INSTANCE;
    }
}
