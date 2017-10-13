package com.robillo.readrush.ui.login;

import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

import com.robillo.readrush.ui.base.MvpView;

import java.util.List;

/**
 * Created by robinkamboj on 11/10/17.
 */

public interface LoginMvpView extends MvpView {

    void loadConversations();

    String[] loadArray(@ArrayRes int id);

}
