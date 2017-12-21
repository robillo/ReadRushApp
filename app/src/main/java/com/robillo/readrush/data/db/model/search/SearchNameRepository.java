package com.robillo.readrush.data.db.model.search;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

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

    @SuppressLint("StaticFieldLeak")
    @SuppressWarnings("unused")
    public void deleteAllSearchItems() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                searchNameDao.deleteAllSearchNames();
                return null;
            }
        }.execute();
    }

}
