package com.robillo.readrush.data.network;

import javax.inject.Inject;

/**
 * Created by robinkamboj on 08/10/17.
 */

public class AppApiHelper implements ApiHelper{

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }
}
