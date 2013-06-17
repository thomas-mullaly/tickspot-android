package com.tronix.tickspot.account.impl;

import com.google.inject.Inject;
import com.tronix.tickspot.account.AccountManager;
import com.tronix.tickspot.api.TickSpotCredentials;
import com.tronix.tickspot.settings.CredentialsStore;

public class AccountManagerImpl implements AccountManager {
    private final CredentialsStore mCredentialsStore;

    @Inject
    public AccountManagerImpl(CredentialsStore credentialsStore) {
        mCredentialsStore = credentialsStore;
    }

    @Override
    public boolean isLoggedIn() {
        return mCredentialsStore.hasSavedCredentials();
    }

    @Override
    public TickSpotCredentials getAccount() {
        return mCredentialsStore.getCredentials();
    }

    @Override
    public void putAccount(TickSpotCredentials credentials) {
        mCredentialsStore.putCredentials(credentials);
    }
}
