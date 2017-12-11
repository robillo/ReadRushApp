package com.robillo.readrush.ui.main.library;

import android.widget.ImageView;

import com.robillo.readrush.ui.base.MvpView;

/**
 * Created by robinkamboj on 16/10/17.
 */

public interface LibraryMvpView extends MvpView {

    void loadRushes();

    void checkForExistingRushesOnline();

    void checkForExistingRushesOffline();

    boolean verifyOfflineRushesExist();

    void showNoRushes();

    void loadCoversIntoRushes();

    void loadSingleCoverIntoSingleRush(String cover_url, ImageView rush);

}
