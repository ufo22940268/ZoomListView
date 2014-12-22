package com.bettycc.zoomlistview.library;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by ccheng on 12/21/14.
 */
public class PagerHeaderContainer extends HeaderContainer {

    private int[] mResIds;
    private ViewPager mViewPager;
    private ImageView[] mImageViews;

    public PagerHeaderContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setImageResources(int[] resIds) {
        if (resIds == null || resIds.length == 0) {
            throw new IllegalArgumentException("Resource ids can't be null or empty.");
        }

        mResIds = resIds;

        mImageViews = buildImages(mResIds);

        mViewPager = new ViewPager(getContext());
        mViewPager.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mViewPager);

        setImageResource(mImageViews[0], mResIds[0]);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mResIds.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = mImageViews[position];
                imageView.setImageResource(mResIds[position]);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                setImageResource(getBgView(), mResIds[i]);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public ImageView getBgView() {
        if (mViewPager == null) {
            throw new IllegalStateException("view pager haven't be init.");
        }

        return mImageViews[mViewPager.getCurrentItem()];
    }

    private ImageView[] buildImages(int[] resIds) {
        ImageView[] imageViews = new ImageView[resIds.length];
        for (int i = 0; i < resIds.length; i++) {
            imageViews[i] = new ImageView(getContext());
        }
        return imageViews;
    }
}
