package com.tronix.tickspot.api;

import com.loopj.android.http.*;

public interface TickSpotHttpClient {
    public boolean areValidCredentials(TickSpotCredentials tickSpotCredentials);
}

