package com.tronix.tickspot.api.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class TickSpotTask {
    @Element(name = "id")
    private int mId;

    @Element(name = "name")
    private String mName;

    @Element(name = "position")
    private int mPosition;

    @Element(name = "project_id")
    private int mProjectId;

    @Element(name = "opened_on")
    private String mOpenedOn;

    @Element(name = "closed_on", required = false)
    private String mClosedOn;

    @Element(name = "budget")
    private float mBudget;

    @Element(name = "billable", required = false)
    private boolean mBillable;

    @Element(name = "sum_hours", required = false)
    private float mSumHours;

    @Element(name = "user_count", required = false)
    private int mUserCount;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public int getPosition() {
        return mPosition;
    }

    public int getProjectId() {
        return mProjectId;
    }

    public String getOpenedOn() {
        return mOpenedOn;
    }

    public String getClosedOn() {
        return mClosedOn;
    }

    public float getBudget() {
        return mBudget;
    }

    public boolean getBillable() {
        return mBillable;
    }

    public float getSumHours() {
        return mSumHours;
    }

    public int getUserCount() {
        return mUserCount;
    }
}
