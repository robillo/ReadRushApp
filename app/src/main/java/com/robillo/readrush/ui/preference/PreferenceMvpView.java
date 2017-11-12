package com.robillo.readrush.ui.preference;

import android.content.Intent;

import com.robillo.readrush.ui.base.MvpView;

/**
 * Created by robinkamboj on 13/10/17.
 */

public interface PreferenceMvpView extends MvpView {

    void setUpWindowAnimations();

    void postUserDetails();

    void setApiInterface();

    void validateUser(Intent intent);
}
