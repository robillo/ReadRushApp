package com.robillo.readrush.data.db.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import java.util.List;

/**
 * Created by robinkamboj on 29/11/17.
 */

@Dao
public interface SearchNameDao {

    @Query("SELECT * FROM searchname")
//    LiveData<List<SearchName>> getAllSearchNames();
    LiveData<List<SearchName>> getAllSearchNames();

    @Query("DELETE FROM searchname")
    void deleteAllSearchNames();

    @Insert(onConflict = REPLACE)
    void insertSearchItem(SearchName... item);

}
