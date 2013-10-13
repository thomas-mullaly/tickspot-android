package com.tronix.tickspot.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tronix.tickspot.R;
import com.tronix.tickspot.settings.TimersStore;
import com.tronix.tickspot.timers.Timer;
import com.tronix.tickspot.ui.adapters.TimersArrayAdapter;
import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

import java.util.ArrayList;

public class TimersListFragment extends ListFragment {
    private TimersStore mTimersStore;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        RoboInjector injector = RoboGuice.getInjector(activity.getApplication());
        mTimersStore = injector.getInstance(TimersStore.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<Timer> timers = mTimersStore.getStoredTimers();

        if (timers == null) {
            timers = new ArrayList<Timer>();
        }

        setListAdapter(new TimersArrayAdapter(getActivity(), timers.toArray(new Timer[timers.size()])));
        return inflator.inflate(R.layout.fragment_timers_list, container, false);
    }
}