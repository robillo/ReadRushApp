package com.robillo.readrush.ui.login;

import com.robillo.readrush.data.others.Conversation;
import com.robillo.readrush.ui.base.BasePresenter;
import com.robillo.readrush.ui.base.MvpPresenter;
import com.robillo.readrush.ui.base.MvpView;

import java.util.List;

/**
 * Created by robinkamboj on 11/10/17.
 */

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    List<Conversation> loadLists();

}
