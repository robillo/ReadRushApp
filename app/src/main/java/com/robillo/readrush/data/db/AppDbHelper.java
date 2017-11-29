package com.robillo.readrush.data.db;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by robinkamboj on 08/10/17.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    @SuppressWarnings("WeakerAccess")
    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {

    }
}
