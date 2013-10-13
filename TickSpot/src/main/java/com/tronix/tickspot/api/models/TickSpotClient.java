package com.tronix.tickspot.api.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class TickSpotClient {
    @Element(name = "id")
    private int mId;

    @Element(name = "name")
    private String mName;

    @ElementList(name = "projects", entry = "project")
    private List<TickSpotProject> mProjects;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public List<TickSpotProject> getProjects() {
        return mProjects;
    }
}
