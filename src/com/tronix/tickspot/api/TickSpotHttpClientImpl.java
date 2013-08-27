package com.tronix.tickspot.api;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tronix.tickspot.api.models.TickSpotClient;
import com.tronix.tickspot.api.parsers.TickSpotClientXmlParser;
import com.tronix.tickspot.api.parsers.impl.TickSpotClientXmlParserImpl;
import com.tronix.tickspot.core.Action;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class TickSpotHttpClientImpl implements TickSpotHttpClient {
    private static final String TICK_SPOT_URL_FORMAT = "https://%s.tickspot.com/api/%s?email=%s&password=%s";
    private AsyncHttpClient mAsyncHttpClient;

    public TickSpotHttpClientImpl() {
        mAsyncHttpClient = new AsyncHttpClient();
    }

    private String encodeString(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    public void areValidCredentials(TickSpotCredentials tickSpotCredentials, final Action<Boolean> callback) {
        String email = encodeString(tickSpotCredentials.getEmail());
        String password = encodeString(tickSpotCredentials.getPassword());

        final String url = String.format(TICK_SPOT_URL_FORMAT, tickSpotCredentials.getDomain(), "recent_task", email, password);
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

    public void getClients(TickSpotCredentials tickSpotCredentials, final Action<ArrayList<TickSpotClient>> callback) {
        String email = encodeString(tickSpotCredentials.getEmail());
        String password = encodeString(tickSpotCredentials.getPassword());

        final String url = String.format(TICK_SPOT_URL_FORMAT, tickSpotCredentials.getDomain(), "clients_projects_tasks", email, password);
        mAsyncHttpClient.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                TickSpotClientXmlParser parser = new TickSpotClientXmlParserImpl();
                List<TickSpotClient> clients = null;
                try {
                    clients = parser.parseClients(response);
                } catch (IOException ex) {

                } catch (XmlPullParserException ex) {

                }

                callback.Invoke(new ArrayList<TickSpotClient>(clients));
            }

            @Override
            public void onFailure(Throwable throwable, String s) {
                callback.Invoke(null);
            }
        });
    }
}
