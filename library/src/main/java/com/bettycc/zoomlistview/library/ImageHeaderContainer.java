package com.bettycc.zoomlistview.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by ccheng on 12/22/14.
 */
public class ImageHeaderContainer extends HeaderContainer {

    private final ImageView mBgView;

    public ImageHeaderContainer(Context context, AttributeSet attrs) {
        super(context, attrs);

        mBgView = new ImageView(context);
        mBgView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mBgView);
    }

    @Override
    public ImageView getBgView() {
        return mBgView;
    }

    @Override
    public void setImageResources(int[] resIds) {
        throw new UnsupportedOperationException();
    }
}
