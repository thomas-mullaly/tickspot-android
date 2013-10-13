package com.tronix.tickspot.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.widget.FrameLayout;
import com.tronix.tickspot.R;
import com.tronix.tickspot.api.models.TickSpotClient;
import com.tronix.tickspot.api.models.TickSpotProject;
import com.tronix.tickspot.api.models.TickSpotTask;

public class ProjectSearchActivity extends ActionBarActivity implements ClientListFragment.OnClientProjectSelectedListener {
    private FrameLayout mFragmentContainer;
    private TickSpotClient mSelectedClient;
    private TickSpotProject mSelectedProject;
    private TickSpotTask mSelectedTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project_search);

        mFragmentContainer = (FrameLayout) findViewById(R.id.project_search_fragment_container);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.project_search_fragment_container, new ClientListFragment());
        transaction.commit();
    }

    @Override
    public void OnClientProjectSelected(TickSpotClient client, TickSpotProject project) {
        mSelectedClient = client;
        mSelectedProject = project;


    }
}