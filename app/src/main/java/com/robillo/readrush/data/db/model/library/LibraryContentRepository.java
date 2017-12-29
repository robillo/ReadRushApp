package com.robillo.readrush.data.db.model.library;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by robinkamboj on 29/12/17.
 */

public class LibraryContentRepository {

    private final LibraryCoverContentDao mLibraryCoverContentDao;

    @Inject
    public LibraryContentRepository(LibraryCoverContentDao mLibraryCoverContentDao) {
        this.mLibraryCoverContentDao = mLibraryCoverContentDao;
    }

    public LiveData<List<LibraryCoverContent>> getContentsFromID(String rush_id) {
        return mLibraryCoverContentDao.getContentsFromRushID(rush_id);
    }

    public void deleteContentsForID(String rush_id) {
        mLibraryCoverContentDao.deleteContentsFromRushID(rush_id);
    }

    @SuppressWarnings("unused")
    @SuppressLint("StaticFieldLeak")
    public void insertContentItem(final LibraryCoverContent item) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mLibraryCoverContentDao.insertCoverContents(item);
                return null;
            }
        }.execute();
    }
}
