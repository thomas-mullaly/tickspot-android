package com.tronix.tickspot.api;

import com.tronix.tickspot.core.Action;

public interface TickSpotHttpClient {
    void areValidCredentials(TickSpotCredentials tickSpotCredentials, final Action<Boolean> callback);
}

