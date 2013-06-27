package com.tronix.tickspot.account;

import com.tronix.tickspot.api.TickSpotCredentials;

public interface AccountManager {
    boolean isLoggedIn();

    TickSpotCredentials getAccount();
    void putAccount(TickSpotCredentials credentials);
    void signOut();
}

