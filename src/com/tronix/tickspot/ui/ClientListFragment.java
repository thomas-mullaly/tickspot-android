package com.tronix.tickspot.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tronix.tickspot.R;
import com.tronix.tickspot.api.TickSpotHttpClient;
import com.tronix.tickspot.api.models.TickSpotClient;
import com.tronix.tickspot.core.Action;
import com.tronix.tickspot.settings.CredentialsStore;
import com.tronix.tickspot.ui.adapters.TickSpotClientProjectAdapter;
import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

import java.util.List;

public class ClientListFragment extends ListFragment {
    private SearchView mSearchView;
    private TickSpotHttpClient mTickSpotHttpClient;
    private CredentialsStore mCredentialsStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client_list, container, false);

        mSearchView = (SearchView) view.findViewById(R.id.search_view);
        setUpSearchView();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        RoboInjector injector = RoboGuice.getInjector(activity.getApplication());
        mTickSpotHttpClient = injector.getInstance(TickSpotHttpClient.class);
        mCredentialsStore = injector.getInstance(CredentialsStore.class);
        mTickSpotHttpClient.getClients(mCredentialsStore.getCredentials(), new Action<List<TickSpotClient>>() {
            @Override
            public void Invoke(List<TickSpotClient> param) {
                setListAdapter(new TickSpotClientProjectAdapter(getActivity(), param));
            }
        });
    }

    private void setUpSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setQueryHint("Search for client...");
    }
}
