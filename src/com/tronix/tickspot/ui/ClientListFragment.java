package com.tronix.tickspot.ui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tronix.tickspot.R;

public class ClientListFragment extends ListFragment {
    private SearchView mSearchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_list, container, false);

        mSearchView = (SearchView) view.findViewById(R.id.search_view);

        setUpSearchView();

        return view;
    }

    private void setUpSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setQueryHint("Search for client...");
    }
}
