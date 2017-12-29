package com.robillo.readrush.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.robillo.readrush.data.db.model.library.LibraryCover;
import com.robillo.readrush.data.db.model.library.LibraryCoverContent;
import com.robillo.readrush.data.db.model.library.LibraryCoverContentDao;
import com.robillo.readrush.data.db.model.library.LibraryCoverDao;
import com.robillo.readrush.data.db.model.search.SearchName;
import com.robillo.readrush.data.db.model.search.SearchNameDao;

/**
 * Created by robinkamboj on 29/11/17.
 */

@Database(entities = {SearchName.class, LibraryCover.class, LibraryCoverContent.class}, version = 9, exportSchema = false)
public abstract class RoomAppDatabase extends RoomDatabase {

    public abstract SearchNameDao mSearchNameDao();

    public abstract LibraryCoverDao mLibraryCoverDao();

    public abstract LibraryCoverContentDao mLibraryContentDao();

}
