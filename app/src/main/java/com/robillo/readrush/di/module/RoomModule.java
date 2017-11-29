package com.robillo.readrush.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.robillo.readrush.data.db.RoomAppDatabase;

/**
 * Created by robinkamboj on 30/11/17.
 */

public class RoomModule {

    private final RoomAppDatabase database;

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(
                application,
                RoomAppDatabase.class,
                "usertable.db"
        ).build();
    }

}
