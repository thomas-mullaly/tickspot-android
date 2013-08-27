package com.tronix.tickspot.api.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class TickSpotProject {
    @Element(name = "id")
    private int mId;

    @Element(name = "name")
    private String mName;

    @Element(name = "budget")
    private float mBudget;

    @Element(name = "client_id", required = false)
    private int mClientId;

    @Element(name = "owner_id", required = false)
    private int mOwnerId;

    @Element(name = "opened_on")
    private String mOpenedOn;

    @Element(name = "closed_on", required = false)
    private String mClosedOn;

    @Element(name = "created_at")
    private String mCreatedAt;

    @Element(name = "updated_at")
    private String mUpdatedAt;

    @Element(name = "client_name", required = false)
    private String mClientName;

    @Element(name = "sum_hours", required = false)
    private float mSumHours;

    @Element(name = "user_count", required = false)
    private int mUserCount;

    @ElementList(name = "tasks", entry = "task")
    private List<TickSpotTask> mTasks;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public float getBudget() {
        return mBudget;
    }

    public int getClientId() {
        return mClientId;
    }

    public int getOwnerId() {
        return mOwnerId;
    }

    public String getOpenedOn() {
        return  mOpenedOn;
    }

    public String getClosedOn() {
        return mClosedOn;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public String getmUpdatedAt() {
        return mUpdatedAt;
    }

    public String getClientName() {
        return mClientName;
    }

    public float getSumHours() {
        return mSumHours;
    }

    public int getUserCount() {
        return mUserCount;
    }

    public List<TickSpotTask> getTasks() {
        return mTasks;
    }
}
