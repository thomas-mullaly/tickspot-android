package com.tronix.tickspot.ui.builders;

import com.tronix.tickspot.api.models.TickSpotClient;
import com.tronix.tickspot.ui.models.ClientProjectEntryBase;

import java.util.List;

public interface ClientProjectListBuilder {
    public List<ClientProjectEntryBase> buildList(List<TickSpotClient> clientProjects);
}
