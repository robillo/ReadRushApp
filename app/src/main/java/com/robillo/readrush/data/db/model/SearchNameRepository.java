package com.robillo.readrush.data.db.model;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;
import java.util.logging.Handler;

import javax.inject.Inject;

/**
 * Created by robinkamboj on 30/11/17.
 */

public class SearchNameRepository {

    @SuppressWarnings("FieldCanBeLocal")
    private final SearchNameDao searchNameDao;

    @Inject
    public SearchNameRepository(SearchNameDao searchNameDao){
        this.searchNameDao = searchNameDao;
    }

    public LiveData<List<SearchName>> getAllSearches() {
        return searchNameDao.getAllSearchNames();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertSearchItem(final SearchName item) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                searchNameDao.insertSearchItem(item);
                return null;
            }
        }.execute();
    }

    public void deleteAllSearchItems() {
        searchNameDao.deleteAllSearchNames();
    }

}
