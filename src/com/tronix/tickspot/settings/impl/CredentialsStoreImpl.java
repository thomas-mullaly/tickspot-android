package com.tronix.tickspot.settings.impl;

import android.content.SharedPreferences;
import com.google.inject.Inject;
import com.tronix.tickspot.api.TickSpotCredentials;
import com.tronix.tickspot.settings.CredentialsStore;
import com.tronix.tickspot.settings.SettingsProvider;

public class CredentialsStoreImpl implements CredentialsStore {
    private static final String CREDENTIALS_DOMAIN = "CredentialsDomain";
    private static final String CREDENTIALS_EMAIL = "CredentialsEmail";
    private static final String CREDENTIALS_PASSWORD = "CredentialsPassword";

    private final SharedPreferences mSharedPreferences;

    @Inject
    public CredentialsStoreImpl(SettingsProvider settingsProvider) {
        mSharedPreferences = settingsProvider.getSettings();
    }

    @Override
    public boolean hasSavedCredentials() {
        // TODO: Maybe have a more robust way of checking?
        return mSharedPreferences.contains(CREDENTIALS_PASSWORD);
    }

    @Override
    public void putCredentials(TickSpotCredentials credentials) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        // TODO: Encrypt this information
        editor.putString(CREDENTIALS_DOMAIN, credentials.getDomain());
        editor.putString(CREDENTIALS_EMAIL, credentials.getEmail());
        editor.putString(CREDENTIALS_PASSWORD, credentials.getPassword());

        editor.commit();
    }

    @Override
    public TickSpotCredentials getCredentials() {
        if (!hasSavedCredentials()) {
            return null;
        }

        return new TickSpotCredentials(
                mSharedPreferences.getString(CREDENTIALS_DOMAIN, ""),
                mSharedPreferences.getString(CREDENTIALS_EMAIL, ""),
                mSharedPreferences.getString(CREDENTIALS_PASSWORD, "")
        );
    }
}
