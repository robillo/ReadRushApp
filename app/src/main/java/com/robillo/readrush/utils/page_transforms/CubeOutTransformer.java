package com.robillo.readrush.utils.page_transforms;

import android.view.View;

/**
 * Created by robinkamboj on 11/10/17.
 */

public class CubeOutTransformer extends BaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        view.setPivotX(position < 0f ? view.getWidth() : 0f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationY(90f * position);
    }

    @Override
    public boolean isPagingEnabled() {
        return true;
    }

}