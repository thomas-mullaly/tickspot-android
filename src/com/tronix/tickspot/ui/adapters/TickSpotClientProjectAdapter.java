package com.tronix.tickspot.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import com.tronix.tickspot.R;
import com.tronix.tickspot.ui.models.ClientEntry;
import com.tronix.tickspot.ui.models.ClientProjectEntryBase;
import com.tronix.tickspot.ui.models.ProjectEntry;

import java.net.URLDecoder;
import java.util.*;

public class TickSpotClientProjectAdapter extends ArrayAdapter<ClientProjectEntryBase> {
    private LayoutInflater mLayouInflater;
    private List<ClientProjectEntryBase> mOriginalEntries;
    private Filter mFilter;

    public TickSpotClientProjectAdapter(Context context, List<ClientProjectEntryBase> clientProjects) {
        super(context, R.layout.client_header_list_item, clientProjects);
        mOriginalEntries = new ArrayList<ClientProjectEntryBase>(clientProjects);
        mLayouInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof ProjectEntry) {
            return 0;
        }
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        ClientProjectEntryBase item = getItem(position);

        final boolean isProjectEntry = item instanceof ProjectEntry;

        if (isProjectEntry) {
            ProjectEntryViewHolder viewHolder;

            if (convertView == null) {
                convertView = mLayouInflater.inflate(R.layout.project_list_item, container, false);
                viewHolder = new ProjectEntryViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(android.R.id.text1);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ProjectEntryViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText(item.name);
        } else {
            ClientEntryViewHolder viewHolder;
            if (convertView == null) {
                convertView = mLayouInflater.inflate(R.layout.client_header_list_item, container, false);
                viewHolder = new ClientEntryViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(android.R.id.text1);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ClientEntryViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText(item.name);
        }

        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position) instanceof ProjectEntry;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ClientProjectFilter(mOriginalEntries);
        }
        return mFilter;
    }

    private class ClientProjectFilter extends Filter {
        private final List<ClientProjectEntryBase> mEntries;

        public ClientProjectFilter(List<ClientProjectEntryBase> entries) {
            mEntries = entries;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<ClientProjectEntryBase> filteredResults = new ArrayList<ClientProjectEntryBase>();
            Set<ClientEntry> addedClients = new HashSet<ClientEntry>();

            for (ClientProjectEntryBase entry : mEntries) {
                if (entry instanceof ProjectEntry) {
                    ProjectEntry proj = (ProjectEntry) entry;
                    if (entry.name.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        if (!addedClients.contains(proj.client)) {
                            addedClients.add(proj.client);
                            filteredResults.add(proj.client);
                        }
                        filteredResults.add(proj);
                    }
                }
            }

            results.values = filteredResults;
            results.count = filteredResults.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            List<ClientProjectEntryBase> results = (List<ClientProjectEntryBase>) filterResults.values;
            clear();

            for (ClientProjectEntryBase entry : results) {
                add(entry);
            }

            notifyDataSetChanged();
        }
    }

    private static class ClientEntryViewHolder {
        public TextView textView;
    }

    private static class ProjectEntryViewHolder {
        public TextView textView;
    }
}
