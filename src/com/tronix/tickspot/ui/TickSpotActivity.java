package com.tronix.tickspot.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
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

    private ListView mTickSpotDrawer;
    private FrameLayout mTickSpotContentFrame;

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

        mTickSpotDrawer = (ListView) findViewById(R.id.tick_spot_drawer);
        mTickSpotDrawer.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, new String[] { "Test" }));
        mTickSpotContentFrame = (FrameLayout) findViewById(R.id.tick_spot_content_frame);
    }
}
