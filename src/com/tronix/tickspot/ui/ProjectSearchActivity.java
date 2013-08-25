package com.tronix.tickspot.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.widget.FrameLayout;
import com.tronix.tickspot.R;

public class ProjectSearchActivity extends ActionBarActivity {
    private FrameLayout mFragmentContainer;

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
}