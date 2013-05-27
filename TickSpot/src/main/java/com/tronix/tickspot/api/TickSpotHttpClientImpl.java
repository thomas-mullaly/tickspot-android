package com.tronix.tickspot.api;

import com.loopj.android.http.AsyncHttpClient;

public class TickSpotHttpClientImpl implements TickSpotHttpClient {
    private AsyncHttpClient mAsyncHttpClient;

    public TickSpotHttpClientImpl() {
        mAsyncHttpClient = new AsyncHttpClient();
    }

    public boolean areValidCredentials(TickSpotCredentials tickSpotCredentials) {
        return true;
    }
}
