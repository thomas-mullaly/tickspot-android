package com.tronix.tickspot.core;

import android.app.Application;
import com.tronix.tickspot.ioc.TickSpotModule;
import roboguice.RoboGuice;

public class TickSpotApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE,
                RoboGuice.newDefaultRoboModule(this), new TickSpotModule());
    }
}
