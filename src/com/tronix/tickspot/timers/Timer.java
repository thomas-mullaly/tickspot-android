package com.tronix.tickspot.timers;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class Timer implements Parcelable {
    private static final DateFormat mDateFormat = DateFormat.getDateInstance();
    private String mComments;
    private Date mStartTime;
    private long mElapsedMinutes;

    private Timer(Parcel parcel) {
        mComments = parcel.readString();
        try {
            mStartTime = mDateFormat.parse(parcel.readString());
        } catch (ParseException e) { }
        mElapsedMinutes = parcel.readLong();
    }

    public long getElapsedMinutes() {
        return mElapsedMinutes;
    }

    public void setElapsedMinutes(long elapsedMinutes) {
        mElapsedMinutes = elapsedMinutes;
    }

    public String getComments() {
        return mComments;
    }

    public void setComments(String comments) {
        mComments = comments;
    }

    public Date getStartTime() {
        return mStartTime;
    }

    public void setStartTime(Date startTime) {
        mStartTime = startTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mComments);
        parcel.writeString(mDateFormat.format(mStartTime));
        parcel.writeLong(mElapsedMinutes);
    }

    public static final Parcelable.Creator<Timer> CREATOR =
        new Creator<Timer>() {
            @Override
            public Timer createFromParcel(Parcel parcel) {
                return new Timer(parcel);
            }

            @Override
            public Timer[] newArray(int i) {
                return new Timer[i];  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
}
