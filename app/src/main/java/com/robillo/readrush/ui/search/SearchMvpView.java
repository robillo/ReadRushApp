package com.robillo.readrush.ui.search;

import com.robillo.readrush.ui.base.MvpView;

/**
 * Created by robinkamboj on 22/10/17.
 */

public interface SearchMvpView extends MvpView {

    void loadDefaultFeaturedBooks();

    void loadSuggestions(String search_tag);

    void showSearchHistory();

}
