package com.robillo.readrush.data.db.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by robinkamboj on 29/11/17.
 */

@Dao
public interface SearchNameDao {

    @Query("SELECT * FROM USER_TABLE")
    List<SearchName> getAllSearchNames();

    @Query("DELETE FROM USER_TABLE")
    void deleteAllSearchNames();

}
