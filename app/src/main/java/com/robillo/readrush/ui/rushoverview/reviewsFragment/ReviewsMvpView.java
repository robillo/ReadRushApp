package com.robillo.readrush.ui.rushoverview.reviewsFragment;

import com.robillo.readrush.ui.base.MvpView;

/**
 * Created by robinkamboj on 24/10/17.
 */

public interface ReviewsMvpView extends MvpView {

    void fetchReviews(String rushId);

}
