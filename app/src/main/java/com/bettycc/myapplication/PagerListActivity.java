package com.bettycc.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bettycc.zoomlistview.library.ZoomListView;


public class PagerListActivity extends ActionBarActivity {

    private static final String[] ITEM_WORDS = {
            "Pull",
            "me",
            "to",
            "enlarge",
            "header",
            ".",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ZoomListView listView = (ZoomListView) findViewById(R.id.list);
        listView.setHeaderResources(new int[]{R.drawable.demo, R.drawable.demo1});
        View view = getLayoutInflater().inflate(R.layout.header_container, listView.getHeaderView(), false);
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
