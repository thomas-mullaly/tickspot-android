package com.tronix.tickspot.api;

import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tronix.tickspot.core.Action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TickSpotHttpClientImpl implements TickSpotHttpClient {
    private AsyncHttpClient mAsyncHttpClient;

    public TickSpotHttpClientImpl() {
        mAsyncHttpClient = new AsyncHttpClient();
    }

    public void areValidCredentials(TickSpotCredentials tickSpotCredentials, final Action<Boolean> callback) {
        String email = "";
        String password = "";

        try {
            email = URLEncoder.encode(tickSpotCredentials.getEmail(), "UTF-8");
            password = URLEncoder.encode(tickSpotCredentials.getPassword(), "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
        }

        final String url = String.format("https://%s.tickspot.com/api/recent_tasks?email=%s&password=%s",
                tickSpotCredentials.getDomain(), email, password);
        mAsyncHttpClient.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                callback.Invoke(true);
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                callback.Invoke(false);
            }
        });
    }
}
