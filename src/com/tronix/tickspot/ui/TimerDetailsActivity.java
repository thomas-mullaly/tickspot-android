package com.tronix.tickspot.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.tronix.tickspot.R;

public class TimerDetailsActivity extends ActionBarActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_timer_details);
    }
}