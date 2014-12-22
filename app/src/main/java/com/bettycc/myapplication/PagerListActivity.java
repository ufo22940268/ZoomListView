package com.bettycc.myapplication;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bettycc.viewpagerindicator.CirclePageIndicator;
import com.bettycc.zoomlistview.library.ZoomListView;


public class PagerListActivity extends ActionBarActivity {

    private static final String[] ITEM_WORDS = {
            "Pull",
            "me",
            "to",
            "enlarge",
            "header",
            "image",
            ".",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ZoomListView listView = (ZoomListView) findViewById(R.id.list);
        listView.setHeaderResources(new int[]{R.drawable.demo, R.drawable.demo1, R.drawable.demo2});
        View view = getLayoutInflater().inflate(R.layout.pager_header_container, listView.getHeaderView(), false);
        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        ViewPager viewPager = listView.getViewPager();
        circlePageIndicator.setViewPager(viewPager);
        circlePageIndicator.setOnPageChangeListener(listView.getPagerHeaderContainer().getOnPageChangeListener());
        listView.setHeaderContentView(view);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 20;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView itemView = (TextView)LayoutInflater.from(PagerListActivity.this).inflate(android.R.layout.simple_list_item_1,
                        parent, false);
                itemView.setText(ITEM_WORDS[position%ITEM_WORDS.length]);
                return itemView;
            }
        });
    }
}
