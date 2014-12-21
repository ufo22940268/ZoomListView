package com.bettycc.zoomlistview.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by ccheng on 12/19/14.
 */
public class HeaderContainer extends FrameLayout {

    private final ImageView mBgView;
    private int mInitWidth;
    private int mInitHeight;
    private float mScale = 1.0f;
    private ValueAnimator mRestoreAnimator;
    private Matrix mMatrix;
    private double mInitScale = 1.0;
    private int mBitmapWidth;
    private int mBitmapHeight;

    public HeaderContainer(Context context, AttributeSet attrs) {
        super(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZoomListView);
        mInitHeight = typedArray.getDimensionPixelSize(R.styleable.ZoomListView_headerHeight, 0);
        typedArray.recycle();

        mBgView = new ImageView(getContext());
        mBgView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mBgView);
    }

    public void setImageResource(int resId) {
        mBgView.setImageResource(resId);

        mInitWidth = getResources().getDisplayMetrics().widthPixels;
        if (mInitHeight == 0) {
            mInitHeight = (int) (mInitWidth * 0.6);
        }
        mBgView.setScaleType(ImageView.ScaleType.MATRIX);
        mMatrix = new Matrix();
        mBgView.setImageMatrix(mMatrix);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        mBitmapWidth = bitmap.getWidth();
        mBitmapHeight = bitmap.getHeight();
        float s = mInitHeight / (float) mBitmapHeight;
        s = Math.max(s, mInitWidth / (float) mBitmapWidth);
        mInitScale = s;

        updateMatrix(s);

        updateLayoutParams(mInitWidth, mInitHeight);
    }

    private void updateMatrix(float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        float tx = (mBitmapWidth * scale - mInitWidth) / 2;
        float ty = (mBitmapHeight * scale - mInitHeight) / 2;
        matrix.postTranslate(-tx, -ty);

        mBgView.setImageMatrix(matrix);
    }

    private void updateLayoutParams(int initWidth, int initHeight) {
        setLayoutParams(new AbsListView.LayoutParams(initWidth, initHeight));
    }

    public int getInitHeight() {
        return mInitHeight;
    }

    public void scale(float scale) {
        if (!isAnimating()) {
            scaleInternal(scale);
        }
    }

    public boolean isAnimating() {
        return mRestoreAnimator != null && mRestoreAnimator.isRunning();
    }

    private void scaleInternal(float scale) {
        mScale = scale;
        updateMatrix((float) (mScale * mInitScale));
        updateLayoutParams(((int) (mInitWidth * scale)), ((int) (mInitHeight * scale)));
    }

    public float getScale() {
        return mScale;
    }

    public void restoreScale() {
        mRestoreAnimator = ValueAnimator.ofFloat(mScale, 1);
        mRestoreAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = ((Float) animation.getAnimatedValue()).floatValue();
                scaleInternal(scale);
            }
        });
        mRestoreAnimator.setInterpolator(new AccelerateInterpolator());
        mRestoreAnimator.setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
        mRestoreAnimator.start();
    }

    public boolean isScaled() {
        return getScale() != mInitScale;
    }
}