package com.tronix.tickspot.ui;

import android.content.Intent;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.tronix.tickspot.R;

public class TickSpotActivity extends SherlockFragmentActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
