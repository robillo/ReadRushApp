package com.robillo.readrush.ui.splash;

import com.robillo.readrush.ui.base.MvpView;

/**
 * Created by robinkamboj on 10/10/17.
 */

public interface SplashMvpView extends MvpView {

    void openOnBoardActivity();

    void openLoginActivity();

    void openMainActivity();

    void startSyncService();

}
