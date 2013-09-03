package com.tronix.tickspot.ioc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.tronix.tickspot.account.AccountManager;
import com.tronix.tickspot.account.impl.AccountManagerImpl;
import com.tronix.tickspot.api.TickSpotHttpClient;
import com.tronix.tickspot.api.TickSpotHttpClientImpl;
import com.tronix.tickspot.settings.CredentialsStore;
import com.tronix.tickspot.settings.SettingsProvider;
import com.tronix.tickspot.settings.TimersStore;
import com.tronix.tickspot.settings.impl.CredentialsStoreImpl;
import com.tronix.tickspot.settings.impl.SettingsProviderImpl;
import com.tronix.tickspot.settings.impl.TimersStoreImpl;
import com.tronix.tickspot.ui.builders.ClientProjectListBuilder;
import com.tronix.tickspot.ui.builders.impl.ClientProjectListBuilderImpl;

public class TickSpotModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(SettingsProvider.class).to(SettingsProviderImpl.class);
        binder.bind(CredentialsStore.class).to(CredentialsStoreImpl.class);
        binder.bind(TickSpotHttpClient.class).to(TickSpotHttpClientImpl.class);
        binder.bind(AccountManager.class).to(AccountManagerImpl.class);
        binder.bind(TimersStore.class).to(TimersStoreImpl.class);
        binder.bind(Gson.class).toInstance(new GsonBuilder().create());
        binder.bind(ClientProjectListBuilder.class).to(ClientProjectListBuilderImpl.class);
    }
}
