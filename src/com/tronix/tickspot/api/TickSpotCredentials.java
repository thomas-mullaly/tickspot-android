package com.tronix.tickspot.api;

import android.os.Parcel;
import android.os.Parcelable;

public class TickSpotCredentials implements Parcelable {
    private final String mDomain;
    private final String mEmail;
    private final String mPassword;

    public TickSpotCredentials(String domain, String email, String password) {
        mDomain = domain;
        mEmail = email;
        mPassword = password;
    }

    private TickSpotCredentials(Parcel parcel) {
        mDomain = parcel.readString();
        mEmail = parcel.readString();
        mPassword = parcel.readString();
    }

    public String getDomain() {
        return mDomain;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mDomain);
        parcel.writeString(mEmail);
        parcel.writeString(mPassword);
    }

    public static final Parcelable.Creator<TickSpotCredentials> CREATOR =
        new Parcelable.Creator<TickSpotCredentials>()
        {
            @Override
            public TickSpotCredentials createFromParcel(Parcel parcel) {
                return new TickSpotCredentials(parcel);
            }

            @Override
            public TickSpotCredentials[] newArray(int i) {
                return new TickSpotCredentials[i];
            }
        };
}
