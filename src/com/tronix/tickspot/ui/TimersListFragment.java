package com.tronix.tickspot.ui;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tronix.tickspot.R;

public class TimersListFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
        return inflator.inflate(R.layout.fragment_timers_list, container, false);
    }
}
