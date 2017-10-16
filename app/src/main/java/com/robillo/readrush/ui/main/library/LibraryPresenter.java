package com.robillo.readrush.ui.main.library;

import com.robillo.readrush.data.DataManager;
import com.robillo.readrush.ui.base.BasePresenter;
import com.robillo.readrush.ui.base.MvpPresenter;
import com.robillo.readrush.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by robinkamboj on 16/10/17.
 */

public class LibraryPresenter<V extends LibraryMvpView> extends BasePresenter<V> implements LibraryMvpPresenter<V> {

    @Inject
    public LibraryPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
