package com.tronix.tickspot.ui.models;

import com.tronix.tickspot.api.models.TickSpotProject;

public class ProjectEntry extends ClientProjectEntryBase {
    public final TickSpotProject project;
    public final ClientEntry client;

    public ProjectEntry(TickSpotProject project, ClientEntry client) {
        super(project.getName());
        this.project = project;
        this.client = client;
    }
}
