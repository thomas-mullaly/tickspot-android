package com.tronix.tickspot.api;

import com.loopj.android.http.*;

public class TickSpotHttpClient {
    private AsyncHttpClient mAsyncHttpClient;

    public TickSpotHttpClient() {
        mAsyncHttpClient = new AsyncHttpClient();
    }
}
