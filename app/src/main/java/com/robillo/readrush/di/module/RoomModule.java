package com.robillo.readrush.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.robillo.readrush.data.db.RoomAppDatabase;
import com.robillo.readrush.data.db.model.SearchNameDao;
import com.robillo.readrush.data.db.model.SearchNameRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robinkamboj on 30/11/17.
 */

@Module
public class RoomModule {

    private final RoomAppDatabase database;

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(
                application,
                RoomAppDatabase.class,
                "apptable.db"
        ).build();
    }

    @Singleton
    @Provides
    RoomAppDatabase providesRoomDatabase() {
        return database;
    }

    @Singleton
    @Provides
    SearchNameDao providesProductDao(RoomAppDatabase database) {
        return database.mSearchNameDao();
    }

    @Provides
    @Singleton
    SearchNameRepository provideListItemRepository(SearchNameDao searchNameDao){
        return new SearchNameRepository(searchNameDao);
    }

}
