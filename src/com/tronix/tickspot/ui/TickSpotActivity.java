package com.tronix.tickspot.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.tronix.tickspot.R;
import com.tronix.tickspot.account.AccountManager;
import com.tronix.tickspot.api.TickSpotCredentials;
import com.tronix.tickspot.utils.AccountUtils;
import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

import java.util.ArrayList;

public class TickSpotActivity extends ActionBarActivity {
    private static final String SAVED_SELECTED_DRAWER_INDEX = "SelectedDrawerIndex";

    private TickSpotCredentials mCredentials;
    private RoboInjector mRoboInjector;
    private AccountManager mAccountManager;

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    private ArrayList<Fragment> mDrawerFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRoboInjector = RoboGuice.getInjector(getApplication());
        mAccountManager = mRoboInjector.getInstance(AccountManager.class);

        if (!mAccountManager.isLoggedIn()) {
            AccountUtils.startLoginFlow(this, new Intent(this, TickSpotActivity.class));
            finish();
            return;
        }

        mCredentials = mAccountManager.getAccount();

        setContentView(R.layout.activity_tickspot);

        setUpNavigationDrawer();

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(SAVED_SELECTED_DRAWER_INDEX)) {
                setSelectedDrawerIndex(savedInstanceState.getInt(SAVED_SELECTED_DRAWER_INDEX));
            }
        } else {
            setSelectedDrawerIndex(0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (!mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.openDrawer(mDrawerList);
            } else {
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        } else if (item.getItemId() == R.id.menu_sign_out) {
            mAccountManager.signOut();
            AccountUtils.startLoginFlow(this, new Intent(this, TickSpotActivity.class));
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tickspot_actionbar_menu, menu);
        return true;
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

    private void setSelectedDrawerIndex(int index) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.tick_spot_content_frame, mDrawerFragments.get(index)).commit();
    }

    private void setUpNavigationDrawer() {
        mDrawerFragments = new ArrayList<Fragment>();
        mDrawerFragments.add(new TimersFragment());

        mDrawerLayout = (DrawerLayout) findViewById(R.id.tick_spot_drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerList = (ListView) findViewById(R.id.tick_spot_drawer_list);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, new String[] { "Timers" }));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

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

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            mDrawerList.setItemChecked(i, true);
            mDrawerLayout.closeDrawer(mDrawerList);

            setSelectedDrawerIndex(i);
        }
    }
}
