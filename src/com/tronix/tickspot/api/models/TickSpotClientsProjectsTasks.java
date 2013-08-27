package com.tronix.tickspot.api.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false, name = "clients")
public class TickSpotClientsProjectsTasks {
    @Attribute
    public String type;

    @ElementList(inline = true, entry = "client")
    private List<TickSpotClient> mClients;

    public List<TickSpotClient> getClients() {
        return mClients;
    }
}