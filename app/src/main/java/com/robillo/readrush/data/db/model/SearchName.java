package com.robillo.readrush.data.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by robinkamboj on 29/11/17.
 */

@Entity
public class SearchName {

    @PrimaryKey
    public String mSearchName;

}
