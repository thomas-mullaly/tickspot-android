package com.tronix.tickspot.settings;

import android.content.SharedPreferences;

public interface SettingsProvider {
    SharedPreferences getSettings();
}
