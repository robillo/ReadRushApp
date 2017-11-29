package com.robillo.readrush.data.db.model;

import android.arch.lifecycle.LiveData;

import java.util.List;

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

    public void insertSearchItem(SearchName item) {
        searchNameDao.insertSearchItem(item);
    }

    public void deleteAllSearchItems() {
        searchNameDao.deleteAllSearchNames();
    }

}
