package com.robillo.readrush.data.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by robinkamboj on 29/11/17.
 */

@Entity
public class SearchName {

    public SearchName(@NonNull String mSearchName) {
        this.mSearchName = mSearchName;
    }

    @SuppressWarnings("NullableProblems")
    @NonNull
    @PrimaryKey
    public String mSearchName;

    @NonNull
    public String getmSearchName() {
        return mSearchName;
    }

    public void setmSearchName(@NonNull String mSearchName) {
        this.mSearchName = mSearchName;
    }
}
