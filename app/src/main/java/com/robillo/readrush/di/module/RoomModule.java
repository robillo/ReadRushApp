package com.robillo.readrush.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.robillo.readrush.data.db.RoomAppDatabase;
import com.robillo.readrush.data.db.model.library.LibraryCoverDao;
import com.robillo.readrush.data.db.model.library.LibraryCoverRepository;
import com.robillo.readrush.data.db.model.search.SearchNameDao;
import com.robillo.readrush.data.db.model.search.SearchNameRepository;

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
    SearchNameDao providesProductDaoSearch(RoomAppDatabase database) {
        return database.mSearchNameDao();
    }

    @Provides
    @Singleton
    SearchNameRepository provideListItemRepositorySearch(SearchNameDao searchNameDao){
        return new SearchNameRepository(searchNameDao);
    }

    @Singleton
    @Provides
    LibraryCoverDao providesProductDaoLibrary(RoomAppDatabase database) {
        return database.mLibraryCoverDao();
    }

    @Provides
    @Singleton
    LibraryCoverRepository provideListItemRepositoryLibrary(LibraryCoverDao libraryCoverDao){
        return new LibraryCoverRepository(libraryCoverDao);
    }

}
