package com.tronix.tickspot.settings.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.inject.Inject;
import com.tronix.tickspot.settings.SettingsProvider;

public class SettingsProviderImpl implements SettingsProvider {
    private static final String TICKSPOT_PREFERENCES = "TickSpotPreferences";
    private final SharedPreferences mSettings;

    @Inject
    public SettingsProviderImpl(Context context) {
        mSettings = context.getSharedPreferences(TICKSPOT_PREFERENCES, Context.MODE_PRIVATE);
    }


    @Override
    public SharedPreferences getSettings() {
        return mSettings;
    }
}
