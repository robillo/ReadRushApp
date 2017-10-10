package com.robillo.readrush.data.db;

import android.content.Context;

import com.robillo.readrush.di.ApplicationContext;
import com.robillo.readrush.di.DatabaseInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by robinkamboj on 08/10/17.
 */

@Singleton
public class DbOpenHelper{

    @Inject
    public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {

    }

}
