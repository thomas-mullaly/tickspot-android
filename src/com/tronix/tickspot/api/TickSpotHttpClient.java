package com.tronix.tickspot.api;

import com.tronix.tickspot.core.Action;

import java.util.ArrayList;

public interface TickSpotHttpClient {
    void areValidCredentials(TickSpotCredentials tickSpotCredentials, final Action<Boolean> callback);
    void getClients(TickSpotCredentials tickSpotCredentials, final Action<ArrayList<TickSpotClient>> callback);
}

