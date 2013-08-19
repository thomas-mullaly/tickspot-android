package com.tronix.tickspot.ui;

import android.os.Bundle;
import com.tronix.tickspot.R;

public class TimerDetailsActivity extends BaseDetailActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timer_details);

        super.setUpNavigationDrawer();
    }
}