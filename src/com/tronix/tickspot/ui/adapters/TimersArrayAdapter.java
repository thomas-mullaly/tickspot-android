package com.tronix.tickspot.ui.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.tronix.tickspot.R;
import com.tronix.tickspot.timers.Timer;

public class TimersArrayAdapter extends ArrayAdapter<Timer> {
    public TimersArrayAdapter(Context context, Timer[] timers) {
        super(context, R.layout.timers_list_item, timers);
    }
}
