package com.robillo.readrush.data.db.model.search

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by robinkamboj on 29/11/17.
 */

@Entity
class SearchName(@field:PrimaryKey
                 var mSearchName: String) {

    fun getmSearchName(): String {
        return mSearchName
    }

    fun setmSearchName(mSearchName: String) {
        this.mSearchName = mSearchName
    }
}
