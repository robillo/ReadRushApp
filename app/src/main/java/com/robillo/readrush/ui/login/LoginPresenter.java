package com.robillo.readrush.ui.login;

import com.robillo.readrush.data.DataManager;
import com.robillo.readrush.data.others.Conversation;
import com.robillo.readrush.ui.base.BasePresenter;
import com.robillo.readrush.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by robinkamboj on 11/10/17.
 */

@SuppressWarnings("WeakerAccess")
public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    @Inject
    public LoginPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public List<Conversation> loadLists(String[] ken, String[] hint, String[] primary, String[] secondary) {
        List<Conversation> mList = new ArrayList<>();

        return null;
    }
}
