package com.tronix.tickspot.settings;

import com.tronix.tickspot.api.TickSpotCredentials;

public interface CredentialsStore {
    boolean hasSavedCredentials();
    void putCredentials(TickSpotCredentials credentials);
    TickSpotCredentials getCredentials();
}
