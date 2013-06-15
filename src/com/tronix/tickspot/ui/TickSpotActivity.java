package com.tronix.tickspot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.tronix.tickspot.R;
import com.tronix.tickspot.api.TickSpotCredentials;
import com.tronix.tickspot.settings.CredentialsStore;
import roboguice.RoboGuice;

public class TickSpotActivity extends SherlockFragmentActivity {
    private static final int TICKSPOT_LOGIN_REQUEST = 1;
    private CredentialsStore mCredentialsStore;
    private TickSpotCredentials mCredentials;

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickspot);

        mCredentialsStore = RoboGuice.getInjector(getApplication()).getInstance(CredentialsStore.class);
        if (mCredentialsStore.hasSavedCredentials()) {
            mCredentials = mCredentialsStore.getCredentials();
            Toast.makeText(this, "Loaded credentials: " + mCredentials.getEmail(), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, TICKSPOT_LOGIN_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TICKSPOT_LOGIN_REQUEST && resultCode == RESULT_OK) {
            TickSpotCredentials credentials = data.getParcelableExtra(LoginActivity.TICKSPOT_LOGIN_CREDENTIALS_EXTRA);
            mCredentialsStore.putCredentials(credentials);
            Toast.makeText(this, "Worked: " + credentials.getEmail(), Toast.LENGTH_LONG).show();
        }
    }
}
