package com.tronix.tickspot.api;

public class TickSpotCredentials {
    private final String mDomain;
    private final String mEmail;
    private final String mPassword;

    public TickSpotCredentials(String domain, String email, String password) {
        mDomain = domain;
        mEmail = email;
        mPassword = password;
    }
}
