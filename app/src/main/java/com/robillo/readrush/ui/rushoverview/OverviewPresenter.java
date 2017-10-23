package com.robillo.readrush.ui.rushoverview;

import com.robillo.readrush.data.DataManager;
import com.robillo.readrush.ui.base.BasePresenter;
import com.robillo.readrush.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by robinkamboj on 23/10/17.
 */

public class OverviewPresenter<V extends OverviewMvpView> extends BasePresenter<V> implements OverviewMvpPresenter<V> {

    @Inject
    public OverviewPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
