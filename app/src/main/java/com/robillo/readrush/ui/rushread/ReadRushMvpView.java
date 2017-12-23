package com.robillo.readrush.ui.rushread;

import com.robillo.readrush.data.network.retrofit.model.Content;
import com.robillo.readrush.ui.base.MvpView;

import java.util.List;

/**
 * Created by robinkamboj on 09/12/17.
 */

public interface ReadRushMvpView extends MvpView {

    void setLightTheme();

    void setDarkTheme();

    void getContent();

    void setFragmentsForContents(List<Content> contents);

    void hideShowCustomizeLayout();

    void hideCustomizeLayout();

    void showCustomizeLayout();

    void refreshFragments();

    void setInitialTheme();

    void setFontSize(int sizeNumber);

}
