package com.robillo.readrush.data.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by robinkamboj on 29/11/17.
 */

@Entity
public class SearchName {

    @SuppressWarnings("NullableProblems")
    @NonNull
    @PrimaryKey
    String mSearchName;

}
