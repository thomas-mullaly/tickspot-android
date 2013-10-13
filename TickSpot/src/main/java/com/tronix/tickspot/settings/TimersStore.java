package com.tronix.tickspot.settings;

import com.tronix.tickspot.timers.Timer;

import java.util.ArrayList;

public interface TimersStore {
    public boolean hasStoredTimers();
    public ArrayList<Timer> getStoredTimers();
    public void putStoredTimers(ArrayList<Timer> timers);
    public void removeStoredTimers();
}
