package com.tronix.tickspot.ui.models;

import com.tronix.tickspot.api.models.TickSpotClient;

public class ClientEntry extends ClientProjectEntryBase {
    public final TickSpotClient client;

    public ClientEntry(TickSpotClient client) {
        super(client.getName());
        this.client = client;
    }
}
