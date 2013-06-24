package com.tronix.tickspot.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.google.inject.Inject;
import com.tronix.tickspot.R;
import com.tronix.tickspot.account.AccountManager;
import com.tronix.tickspot.api.TickSpotCredentials;
import com.tronix.tickspot.api.TickSpotHttpClient;
import com.tronix.tickspot.core.Action;
import roboguice.RoboGuice;
import roboguice.inject.InjectView;
import roboguice.inject.RoboInjector;

public class LoginActivity extends SherlockActivity {
    private static final String EXTRA_PREFIX = "com.tronix.tickspot.extra.";

    public static final String EXTRA_FINISH_INTENT = EXTRA_PREFIX + "FinishIntent";

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mDomainEditText;
    private Button mSignInButton;
    private View mLoginFormView;
    private View mLoginStatusView;
    private TextView mLoginStatusMessageView;

    private RoboInjector mRoboInjector;
    private TickSpotHttpClient mTickSpotClient;
    private AccountManager mAccountManager;
    private Intent mFinishIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mRoboInjector = RoboGuice.getInjector(getApplication());
        mTickSpotClient = mRoboInjector.getInstance(TickSpotHttpClient.class);
        mAccountManager = mRoboInjector.getInstance(AccountManager.class);

        mEmailEditText = (EditText) findViewById(R.id.login_email);
        mPasswordEditText = (EditText) findViewById(R.id.login_password);
        mDomainEditText = (EditText) findViewById(R.id.login_domain);
        mSignInButton = (Button) findViewById(R.id.login_sign_in_button);
        mLoginFormView = findViewById(R.id.login_form);
        mLoginStatusView = findViewById(R.id.login_status);
        mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

        final Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_FINISH_INTENT)) {
            mFinishIntent = intent.getParcelableExtra(EXTRA_FINISH_INTENT);
        }

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void resetFieldErrors() {
        mDomainEditText.setError(null);
        mPasswordEditText.setError(null);
        mEmailEditText.setError(null);
    }

    private boolean validateFields() {
        boolean isValid = true;
        String domain = mDomainEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if (TextUtils.isEmpty(domain)) {
            mDomainEditText.setError(getString(R.string.error_field_required));
            mDomainEditText.requestFocus();
            isValid = false;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailEditText.setError(getString(R.string.error_field_required));
            mEmailEditText.requestFocus();
            isValid = false;
        } else if (!email.contains("@")) {
            mEmailEditText.setError(getString(R.string.error_invalid_email));
            mEmailEditText.requestFocus();
            isValid = false;
        }

        if (TextUtils.isEmpty(password)) {
            mPasswordEditText.setError(getString(R.string.error_field_required));
            mPasswordEditText.requestFocus();
            isValid = false;
        }
        return isValid;
    }

    private void login() {
        resetFieldErrors();

        if (validateFields()) {
            String domain = mDomainEditText.getText().toString();
            String email = mEmailEditText.getText().toString();
            String password = mPasswordEditText.getText().toString();

            final TickSpotCredentials credentials = new TickSpotCredentials(domain, email, password);

            showProgress(true);

            mTickSpotClient.areValidCredentials(credentials, new Action<Boolean>() {
                @Override
                public void Invoke(Boolean param) {
                    showProgress(false);

                    if (param) {
                        mAccountManager.putAccount(credentials);
                        mFinishIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                        mFinishIntent.setAction(Intent.ACTION_MAIN);
                        mFinishIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(mFinishIntent);
                        finish();
                    } else {
                        // Do something useful.
                    }
                }
            });
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginStatusView.setVisibility(View.VISIBLE);
            mLoginStatusView.animate()
                    .setDuration(shortAnimTime)
                    .alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
                        }
                    });

            mLoginFormView.setVisibility(View.VISIBLE);
            mLoginFormView.animate()
                    .setDuration(shortAnimTime)
                    .alpha(show ? 0 : 1)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                        }
                    });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
