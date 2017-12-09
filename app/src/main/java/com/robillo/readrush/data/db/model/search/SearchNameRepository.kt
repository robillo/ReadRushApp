package com.robillo.readrush.data.db.model.search

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.os.AsyncTask

import javax.inject.Inject

/**
 * Created by robinkamboj on 30/11/17.
 */

class SearchNameRepository @Inject
constructor(private val searchNameDao: SearchNameDao) {

    val allSearches: LiveData<List<SearchName>>
        get() = searchNameDao.allSearchNames

    @SuppressLint("StaticFieldLeak")
    fun insertSearchItem(item: SearchName) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                searchNameDao.insertSearchItem(item)
                return null
            }
        }.execute()
    }

    @Suppress("unused")
    fun deleteAllSearchItems() {
        searchNameDao.deleteAllSearchNames()
    }

}
