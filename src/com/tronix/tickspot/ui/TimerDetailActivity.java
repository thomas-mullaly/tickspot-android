package com.tronix.tickspot.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.tronix.tickspot.R;

public class TimerDetailActivity extends ActionBarActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timer_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}