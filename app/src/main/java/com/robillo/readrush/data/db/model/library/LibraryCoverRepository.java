package com.robillo.readrush.data.db.model.library;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.robillo.readrush.data.db.model.search.SearchName;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by robinkamboj on 09/12/17.
 */

public class LibraryCoverRepository {

    @SuppressWarnings("FieldCanBeLocal")
    private final LibraryCoverDao mLibraryCoverDao;

    @Inject
    public LibraryCoverRepository(LibraryCoverDao mLibraryCoverDao) {
        this.mLibraryCoverDao = mLibraryCoverDao;
    }

    public LiveData<List<LibraryCover>> getAllCovers() {
        return mLibraryCoverDao.getAllCoverItems();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertCoverItem(final LibraryCover item) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mLibraryCoverDao.insertCoverItem(item);
                return null;
            }
        }.execute();
    }

    public void deleteAllCoverItems() {
        mLibraryCoverDao.deleteAllCoverNames();
    }

    @SuppressLint("StaticFieldLeak")
    @SuppressWarnings("unused")
    public void deleteCoverItem(final LibraryCover cover) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mLibraryCoverDao.deleteLibraryCoverItem(cover);
                return null;
            }
        }.execute();
    }

    @SuppressWarnings("unused")
    public LiveData<List<String>> getAllRushIds() {
        return mLibraryCoverDao.getAllRushIds();
    }

    public LiveData<List<LibraryCoverContent>> getContentsForRushID(String rush_id) {
        return mLibraryCoverDao.getContentsForRushID(rush_id);
    }
}
