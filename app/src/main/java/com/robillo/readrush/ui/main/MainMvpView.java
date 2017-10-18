package com.robillo.readrush.ui.main;

import com.robillo.readrush.ui.base.MvpView;

/**
 * Created by robinkamboj on 14/10/17.
 */

public interface MainMvpView extends MvpView {

    void setLibraryFragment();

    void setDiscoverFragment();

    void setProfileFragment();

    void setBottomNavigationTint(int position);

    void refreshLibraryRushes();

    void setUpWindowAnimations();
}
