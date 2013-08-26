package com.tronix.tickspot.api;

public class TickSpotClient {
    private final int mId;
    private final String mName;

    public TickSpotClient(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}