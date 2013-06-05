package com.tronix.tickspot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.tronix.tickspot.R;
import com.tronix.tickspot.api.TickSpotCredentials;

public class TickSpotActivity extends SherlockFragmentActivity {
    private static final int TICKSPOT_LOGIN_REQUEST = 1;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, TICKSPOT_LOGIN_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TICKSPOT_LOGIN_REQUEST && resultCode == RESULT_OK) {
            TickSpotCredentials credentials = data.getParcelableExtra(LoginActivity.TICKSPOT_LOGIN_CREDENTIALS_EXTRA);
            Toast.makeText(this, "Worked: " + credentials.getEmail(), Toast.LENGTH_LONG).show();
        }
    }
}
