package com.robillo.readrush.ui.rushoverview.reviewsFragment;

import com.robillo.readrush.data.DataManager;
import com.robillo.readrush.ui.base.BasePresenter;
import com.robillo.readrush.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by robinkamboj on 24/10/17.
 */

public class ReviewsPresenter<V extends ReviewsMvpView> extends BasePresenter<V> implements ReviewsMvpPresenter<V> {

    @Inject
    public ReviewsPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
