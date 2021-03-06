package com.tronix.tickspot.api;

import com.tronix.tickspot.api.models.TickSpotClient;
import com.tronix.tickspot.core.Action;

import java.util.List;

public interface TickSpotHttpClient {
    void areValidCredentials(TickSpotCredentials tickSpotCredentials, final Action<Boolean> callback);
    void getClients(TickSpotCredentials tickSpotCredentials, final Action<List<TickSpotClient>> callback);
}

