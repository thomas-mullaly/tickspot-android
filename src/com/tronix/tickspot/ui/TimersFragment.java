package com.tronix.tickspot.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.TextView;
import com.tronix.tickspot.R;

public class TimersFragment extends Fragment {
    private TimersListFragment mTimersListFragment;
    private TextView mTimersDetailFragment;
    private boolean mIsDualPane = false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        ActionBarActivity activity = (ActionBarActivity)getActivity();
        activity.getSupportActionBar().setTitle("Timers");
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_timers, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceData) {
        super.onActivityCreated(savedInstanceData);

        mTimersListFragment = (TimersListFragment) getFragmentManager().findFragmentById(R.id.timers_list_fragment);
        mTimersDetailFragment = (TextView) getActivity().findViewById(R.id.timer_details);

        if (mTimersDetailFragment != null) {
            mIsDualPane = true;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.timers_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_timers_add_timer) {
            addTimer();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void addTimer() {
        if (!mIsDualPane) {
            Intent intent = new Intent(getActivity(), TimerDetailsActivity.class);
            getActivity().startActivity(intent);
        }
    }
}
