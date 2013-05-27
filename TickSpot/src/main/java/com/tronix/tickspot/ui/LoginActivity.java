package com.tronix.tickspot.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tronix.tickspot.R;

public class LoginActivity extends Activity {
    public static final String EXTRA_EMAIL = "com.tronix.tickspot.LoginActivity.extra.EMAIL";

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mDomainEditText;
    private Button mSignInButton;
    private View mLoginFormView;
    private View mLoginStatusView;
    private TextView mLoginStatusMessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mEmailEditText = (EditText) findViewById(R.id.login_email);
        mPasswordEditText = (EditText) findViewById(R.id.login_password);
        mDomainEditText = (EditText) findViewById(R.id.login_domain);
        mSignInButton = (Button) findViewById(R.id.login_sign_in_button);

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

            showProgress(true);


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
