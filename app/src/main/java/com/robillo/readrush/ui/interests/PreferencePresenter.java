package com.robillo.readrush.ui.interests;

import com.robillo.readrush.data.DataManager;
import com.robillo.readrush.ui.base.BasePresenter;
import com.robillo.readrush.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by robinkamboj on 13/10/17.
 */

public class PreferencePresenter<V extends PreferenceMvpView> extends BasePresenter<V> implements PreferenceMvpPresenter<V> {

    @Inject
    public PreferencePresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
