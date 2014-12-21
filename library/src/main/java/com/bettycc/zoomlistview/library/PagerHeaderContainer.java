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

    public PagerHeaderContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setImageResources(int[] resIds) {
        mResIds = resIds;

        ViewPager viewPager = new ViewPager(getContext());
        viewPager.setLayoutParams(new LayoutParams(mInitWidth, mInitHeight));
        addView(viewPager);

        viewPager.setAdapter(new PagerAdapter() {
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
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(mResIds[position]);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }
        });
    }
}
