package com.tronix.tickspot.ioc;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.tronix.tickspot.api.TickSpotHttpClient;
import com.tronix.tickspot.api.TickSpotHttpClientImpl;
import com.tronix.tickspot.settings.CredentialsStore;
import com.tronix.tickspot.settings.SettingsProvider;
import com.tronix.tickspot.settings.impl.CredentialsStoreImpl;
import com.tronix.tickspot.settings.impl.SettingsProviderImpl;

public class TickSpotModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(SettingsProvider.class).to(SettingsProviderImpl.class);
        binder.bind(CredentialsStore.class).to(CredentialsStoreImpl.class);
        binder.bind(TickSpotHttpClient.class).to(TickSpotHttpClientImpl.class);
    }
}
