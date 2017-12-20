package com.robillo.readrush.ui.main.discover;

import com.robillo.readrush.ui.base.MvpView;

/**
 * Created by robinkamboj on 16/10/17.
 */

public interface DiscoverMvpView extends MvpView {

    void fetchFeaturedBooks();

    void fetchCollectionNames();

    void fetchTopCoverBooks();

    void fetchCollectionFromCid(String coll_id, String coll_name);

}
