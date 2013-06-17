package com.tronix.tickspot.settings.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.inject.Inject;
import com.tronix.tickspot.settings.SettingsProvider;

public class SettingsProviderImpl implements SettingsProvider {
    private final SharedPreferences mSettings;

    @Inject
    public SettingsProviderImpl(Context context) {
        mSettings = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public SharedPreferences getSettings() {
        return mSettings;
    }
}
