package com.robillo.readrush.data.db.model.library;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by robinkamboj on 09/12/17.
 */

@Dao
public interface LibraryCoverDao {

    @Query("SELECT * FROM library_cover")
    LiveData<List<LibraryCover>> getAllCoverItems();

    @Query("SELECT rush_id from library_cover")
    LiveData<List<String>> getAllRushIds();

    @Query("DELETE FROM library_cover")
    void deleteAllCoverNames();

    @Insert(onConflict = REPLACE)
    void insertCoverItem(LibraryCover... item);

    @Delete
    void deleteLibraryCoverItem(LibraryCover cover);

    @Query("SELECT * FROM LibraryCoverContent WHERE rush_id IS :rush_id")
    List<LibraryCoverContent> getContentsForRushID(String rush_id);
}
