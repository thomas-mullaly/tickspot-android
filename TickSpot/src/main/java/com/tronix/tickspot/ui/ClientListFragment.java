package com.tronix.tickspot.ui;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.tronix.tickspot.R;
import com.tronix.tickspot.api.TickSpotHttpClient;
import com.tronix.tickspot.api.models.TickSpotClient;
import com.tronix.tickspot.api.models.TickSpotProject;
import com.tronix.tickspot.core.Action;
import com.tronix.tickspot.settings.CredentialsStore;
import com.tronix.tickspot.ui.adapters.TickSpotClientProjectAdapter;
import com.tronix.tickspot.ui.builders.ClientProjectListBuilder;
import com.tronix.tickspot.ui.models.ClientProjectEntryBase;
import com.tronix.tickspot.ui.models.ProjectEntry;
import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

import java.util.List;

public class ClientListFragment extends ListFragment implements SearchView.OnQueryTextListener {

    public interface OnClientProjectSelectedListener {
        public void OnClientProjectSelected(TickSpotClient client, TickSpotProject project);
    }

    private TickSpotHttpClient mTickSpotHttpClient;
    private CredentialsStore mCredentialsStore;
    private ClientProjectListBuilder mClientProjectListBuilder;
    private TickSpotClientProjectAdapter mAdapter;
    private SearchView mSearchView;
    private OnClientProjectSelectedListener mCallback;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.client_list_menu, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.search_action);
        mSearchView = (SearchView)MenuItemCompat.getActionView(searchMenuItem);
        setUpSearchView();
    }

    @Override
    public void onStart() {
        super.onStart();
        setEmptyText(getResources().getString(R.string.client_project_list_empty_text));
        mTickSpotHttpClient.getClients(mCredentialsStore.getCredentials(), new Action<List<TickSpotClient>>() {
            @Override
            public void Invoke(List<TickSpotClient> param) {
                List<ClientProjectEntryBase> entries = mClientProjectListBuilder.buildList(param);
                mAdapter = new TickSpotClientProjectAdapter(getActivity(), entries);
                setListAdapter(mAdapter);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        setHasOptionsMenu(true);

        RoboInjector injector = RoboGuice.getInjector(activity.getApplication());
        mTickSpotHttpClient = injector.getInstance(TickSpotHttpClient.class);
        mCredentialsStore = injector.getInstance(CredentialsStore.class);
        mClientProjectListBuilder = injector.getInstance(ClientProjectListBuilder.class);

        if (activity instanceof OnClientProjectSelectedListener) {
            mCallback = (OnClientProjectSelectedListener) activity;
        }
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        ProjectEntry entry = (ProjectEntry) getListAdapter().getItem(position);
        mCallback.OnClientProjectSelected(entry.client.client, entry.project);
    }

    private void setUpSearchView() {
        mSearchView.setIconified(false);
        mSearchView.setQueryHint(getResources().getString(R.string.search_view_query_hint));
        mSearchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        mAdapter.getFilter().filter(s);
        return true;
    }
}
