package com.robillo.readrush.ui.onboard;

import com.robillo.readrush.data.DataManager;
import com.robillo.readrush.ui.base.BasePresenter;
import com.robillo.readrush.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by robinkamboj on 10/10/17.
 */

public class OnboardPresenter<V extends OnboardMvpView> extends BasePresenter<V> implements OnboardMvpPresenter<V> {

    @Inject
    public OnboardPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
