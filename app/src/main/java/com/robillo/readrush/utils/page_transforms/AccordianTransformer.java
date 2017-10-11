package com.robillo.readrush.utils.page_transforms;

import android.view.View;

/**
 * Created by robinkamboj on 11/10/17.
 */

public class AccordianTransformer extends BaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        view.setPivotX(position < 0 ? 0 : view.getWidth());
        view.setScaleX(position < 0 ? 1f + position : 1f - position);
    }

}