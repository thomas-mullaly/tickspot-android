package com.tronix.tickspot.ioc;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.tronix.tickspot.api.TickSpotHttpClient;
import com.tronix.tickspot.api.TickSpotHttpClientImpl;

public class TickSpotModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(TickSpotHttpClient.class).to(TickSpotHttpClientImpl.class);
    }
}
