package com.robillo.readrush.ui.main.library;

import android.widget.ImageView;

import com.robillo.readrush.data.db.model.library.LibraryCover;
import com.robillo.readrush.data.network.retrofit.model.LibraryItem;
import com.robillo.readrush.ui.base.MvpView;

import java.util.List;

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

    void openReadRushScreen(int index);

    void saveLibraryCoversOffline(List<LibraryItem> mItemsList);

    void showDeleteAlertDialog(String bookName, int rushIndex);

    boolean setLongClickListenersRushes(ImageView imageView, int coverIndex);

}
