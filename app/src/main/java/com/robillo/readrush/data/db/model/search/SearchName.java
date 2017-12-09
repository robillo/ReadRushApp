package com.robillo.readrush.data.db.model.search;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by robinkamboj on 29/11/17.
 */

@Entity(tableName = "searchname")
public class SearchName {

    @SuppressWarnings("WeakerAccess")
    @NonNull
    @PrimaryKey
    String mSearchName;

    public SearchName(@NonNull String mSearchName) {
        this.mSearchName = mSearchName;
    }

    @NonNull
    public String getmSearchName() {
        return mSearchName;
    }

    @SuppressWarnings("unused")
    public void setmSearchName(@NonNull String mSearchName) {
        this.mSearchName = mSearchName;
    }
}