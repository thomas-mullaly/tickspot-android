package com.tronix.tickspot.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.*;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.tronix.tickspot.R;
import com.tronix.tickspot.account.AccountManager;
import com.tronix.tickspot.api.TickSpotCredentials;
import com.tronix.tickspot.utils.AccountUtils;
import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

public class TickSpotActivity extends SherlockFragmentActivity {
    private TickSpotCredentials mCredentials;
    private RoboInjector mRoboInjector;
    private AccountManager mAccountManager;

    private ListView mDrawerList;
    private FrameLayout mTickSpotContentFrame;
    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRoboInjector = RoboGuice.getInjector(getApplication());
        mAccountManager = mRoboInjector.getInstance(AccountManager.class);

        if (!mAccountManager.isLoggedIn()) {
            AccountUtils.startLoginFlow(this, getIntent());
            finish();
        }

        mCredentials = mAccountManager.getAccount();

        setContentView(R.layout.activity_tickspot);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.tick_spot_drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerList = (ListView) findViewById(R.id.tick_spot_drawer_list);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, new String[] { "Test" }));
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

        mTickSpotContentFrame = (FrameLayout) findViewById(R.id.tick_spot_content_frame);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (!mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.openDrawer(mDrawerList);
            } else {
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        }

        return super.onOptionsItemSelected(item);
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

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            mDrawerList.setItemChecked(i, true);
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }
}
