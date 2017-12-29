package com.robillo.readrush.data.db.model.library;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by robinkamboj on 29/12/17.
 */

@Dao
public interface LibraryCoverContentDao {

    @Query("SELECT * FROM LibraryCoverContent where rush_id = :rush_id")
    LiveData<List<LibraryCoverContent>> getContentsFromRushID(String rush_id);

    @Query("DELETE FROM library_cover where rush_id = :rush_id")
    void deleteContentsFromRushID(String rush_id);

    @Insert(onConflict = IGNORE)
    void insertCoverContents(LibraryCoverContent contents);

    @Insert(onConflict = IGNORE)
    void insertCoverContentsList(List<LibraryCoverContent> contents);

}
