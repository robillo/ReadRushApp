package com.robillo.readrush.ui.onboard.Fragment;

import com.robillo.readrush.data.DataManager;
import com.robillo.readrush.ui.base.BasePresenter;
import com.robillo.readrush.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by robinkamboj on 10/10/17.
 */

public class OnboardFPresenter<V extends OnboardFMvpView> extends BasePresenter<V> implements OnboardFMvpPresenter<V> {

    public OnboardFPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
