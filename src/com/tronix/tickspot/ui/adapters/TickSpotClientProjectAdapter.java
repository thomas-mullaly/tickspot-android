package com.tronix.tickspot.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.tronix.tickspot.R;
import com.tronix.tickspot.api.models.TickSpotClient;
import com.tronix.tickspot.api.models.TickSpotProject;

import java.util.ArrayList;
import java.util.List;

public class TickSpotClientProjectAdapter extends ArrayAdapter<Object> {
    private List<TickSpotClient> mClientProjects;
    private List<Entry> mFlattenedClientProjects;

    private ClientHeaderHolder mClientHeaderHolder;
    private ProjectHolder mProjectHolder;

    private LayoutInflater mLayouInflater;

    public TickSpotClientProjectAdapter(Context context, List<TickSpotClient> clientProjects) {
        super(context, R.layout.project_list_item);

        mClientProjects = clientProjects;
        mLayouInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        buildItems();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (mFlattenedClientProjects.get(position) instanceof ProjectEntry) {
            return 0;
        }
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        View view = null;

        if (mFlattenedClientProjects.get(position) instanceof ProjectEntry) {
            ProjectEntry projectEntry = (ProjectEntry)mFlattenedClientProjects.get(position);
            view = mLayouInflater.inflate(R.layout.project_list_item, container, false);
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(projectEntry.mProject.getName());
        } else {
            ClientHeaderEntry clientHeaderEntry = (ClientHeaderEntry)mFlattenedClientProjects.get(position);
            view = mLayouInflater.inflate(R.layout.client_header_list_item, container, false);
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(clientHeaderEntry.mClient.getName());
        }

        return view;
    }

    private void buildItems() {
        mFlattenedClientProjects = new ArrayList<Entry>();
        for (TickSpotClient client : mClientProjects) {
            ClientHeaderEntry header = new ClientHeaderEntry();
            header.mClient = client;
            mFlattenedClientProjects.add(header);

            add(header);

            for (TickSpotProject project : client.getProjects()) {
                ProjectEntry projectEntry = new ProjectEntry();
                projectEntry.mProject = project;
                mFlattenedClientProjects.add(projectEntry);

                add(projectEntry);
            }
        }
    }

    private static class ClientHeaderHolder {
        public TextView textView;
    }

    private static class ProjectHolder {
        public TextView textView;
    }

    private static class Entry { }

    private static class ClientHeaderEntry extends Entry {
        public TickSpotClient mClient;
    }

    private static class ProjectEntry extends Entry {
        public TickSpotProject mProject;
    }
}
