package com.robillo.readrush.ui.login.fragment;

import com.robillo.readrush.data.DataManager;
import com.robillo.readrush.ui.base.BasePresenter;
import com.robillo.readrush.ui.login.*;
import com.robillo.readrush.ui.login.LoginMvpPresenter;
import com.robillo.readrush.ui.login.LoginMvpView;
import com.robillo.readrush.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by robinkamboj on 12/10/17.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V>{

    public LoginPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
