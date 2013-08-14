package com.tronix.tickspot.settings.impl;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import com.tronix.tickspot.settings.SettingsProvider;
import com.tronix.tickspot.settings.TimersStore;
import com.tronix.tickspot.timers.Timer;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TimersStoreImpl implements TimersStore {
    private static final String TIMERS_KEY = "Timers";
    private static final Type TIMERS_TYPE_TOKEN = new TypeToken<ArrayList<Timer>>(){}.getType();
    private final SharedPreferences mSharedPreference;
    private final Gson mGson;

    @Inject
    public TimersStoreImpl(SettingsProvider settingsProvider, Gson gson) {
        mSharedPreference = settingsProvider.getSettings();
        mGson = gson;
    }

    @Override
    public boolean hasStoredTimers() {
        return mSharedPreference.contains(TIMERS_KEY);
    }

    @Override
    public ArrayList<Timer> getStoredTimers() {
        return mGson.fromJson(mSharedPreference.getString(TIMERS_KEY, ""), TIMERS_TYPE_TOKEN);
    }

    @Override
    public void putStoredTimers(ArrayList<Timer> timers) {
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putString(TIMERS_KEY, mGson.toJson(timers, TIMERS_TYPE_TOKEN));
        editor.commit();
    }

    @Override
    public void removeStoredTimers() {
        mSharedPreference.edit().remove(TIMERS_KEY).commit();
    }
}
