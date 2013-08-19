package com.tronix.tickspot.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.tronix.tickspot.R;

public class BaseDetailActivity extends ActionBarActivity {
    protected DrawerLayout mDrawerLayout;
    protected ListView mDrawerListView;
    protected ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    protected void setUpNavigationDrawer() {
        Resources resources = getResources();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.tick_spot_drawer_layout);
        mDrawerListView = (ListView) findViewById(R.id.tick_spot_drawer_list);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        String[] drawerItems = resources.getStringArray(R.array.drawer_list_view_items);

        mDrawerListView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, drawerItems));
        mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer,
                R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
            }

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (!mDrawerLayout.isDrawerOpen(mDrawerListView)) {
                mDrawerLayout.openDrawer(mDrawerListView);
            } else {
                mDrawerLayout.closeDrawer(mDrawerListView);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            mDrawerListView.setItemChecked(i, true);
            mDrawerLayout.closeDrawer(mDrawerListView);

            Intent intent = new Intent(BaseDetailActivity.this, TickSpotActivity.class);
            intent.putExtra(TickSpotActivity.EXTRA_FRAGMENT_INDEX_TO_SHOW, i);
            startActivity(intent);
        }
    }
}