package com.robillo.readrush.ui.main.discover;

import com.robillo.readrush.data.DataManager;
import com.robillo.readrush.ui.base.BasePresenter;
import com.robillo.readrush.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by robinkamboj on 16/10/17.
 */

public class DiscoverPresenter<V extends DiscoverMvpView> extends BasePresenter<V> implements DiscoverMvpPresenter<V> {

    @Inject
    public DiscoverPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
