package com.bettycc.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pagerHeader(View view) {
        Intent intent = new Intent(this, PagerListActivity.class);
        startActivity(intent);
    }

    public void imageHeader(View view) {
        Intent intent = new Intent(this, ImageListActivity.class);
        startActivity(intent);
    }
}
