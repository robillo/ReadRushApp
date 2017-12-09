package com.robillo.readrush.ui.rushread;

import com.robillo.readrush.data.DataManager;
import com.robillo.readrush.ui.base.BasePresenter;
import com.robillo.readrush.ui.rushoverview.OverviewMvpPresenter;
import com.robillo.readrush.ui.rushoverview.OverviewMvpView;
import com.robillo.readrush.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by robinkamboj on 09/12/17.
 */

public class ReadRushPresenter<V extends ReadRushMvpView> extends BasePresenter<V> implements ReadRushMvpPresenter<V> {

    @Inject
    public ReadRushPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
