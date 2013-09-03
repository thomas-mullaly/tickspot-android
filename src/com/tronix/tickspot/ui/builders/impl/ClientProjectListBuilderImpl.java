package com.tronix.tickspot.ui.builders.impl;

import com.tronix.tickspot.api.models.TickSpotClient;
import com.tronix.tickspot.api.models.TickSpotProject;
import com.tronix.tickspot.ui.builders.ClientProjectListBuilder;
import com.tronix.tickspot.ui.models.ClientEntry;
import com.tronix.tickspot.ui.models.ClientProjectEntryBase;
import com.tronix.tickspot.ui.models.ProjectEntry;

import java.util.ArrayList;
import java.util.List;

public class ClientProjectListBuilderImpl implements ClientProjectListBuilder {
    @Override
    public List<ClientProjectEntryBase> buildList(List<TickSpotClient> clientProjects) {
        ArrayList<ClientProjectEntryBase> entryList = new ArrayList<ClientProjectEntryBase>();

        for (TickSpotClient client : clientProjects) {
            ClientEntry clientEntry = new ClientEntry(client);
            entryList.add(clientEntry);

            for (TickSpotProject project : client.getProjects()) {
                entryList.add(new ProjectEntry(project, clientEntry));
            }
        }

        return entryList;
    }
}
