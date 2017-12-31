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
                try {
                    mLibraryCoverContentDao.insertCoverContents(item);
                }
                catch (Exception e){
                    //abc
                }
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertContentItems(final List<LibraryCoverContent> items) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for(int i=0; i<items.size(); i++){
                    try {
                        mLibraryCoverContentDao.insertCoverContents(items.get(i));
                    }
                    catch (Exception e){
                        //abc
                    }
                }
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void insertContentItemsList(final List<LibraryCoverContent> items) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    mLibraryCoverContentDao.insertCoverContentsList(items);
                }
                catch (Exception e){
                    //abc
                }
                return null;
            }
        }.execute();
    }
}
