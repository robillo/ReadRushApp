package com.robillo.readrush.data.db.model;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
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

    @SuppressLint("StaticFieldLeak")
    public List<SearchName> getAllSearches() {
//        LiveData<List<SearchName>> mList = searchNameDao.getAllSearchNames();
        new AsyncTask<Void, Void, List<SearchName>>() {
            @Override
            protected List<SearchName> doInBackground(Void... voids) {
                List<SearchName> mList = searchNameDao.getAllSearchNames();
                Log.e("SEARCH NAMES LIST ", " " + (mList != null ? mList.size() + mList.get(0).getmSearchName() : 1000));
                return  mList;
            }
        }.execute();
        List<SearchName> msearch = new ArrayList<>();
        msearch.add(new SearchName("test1"));
        msearch.add(new SearchName("test2"));
        msearch.add(new SearchName("test3"));
        return msearch;
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
