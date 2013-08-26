package com.tronix.tickspot.api.parsers.impl;

import com.tronix.tickspot.api.TickSpotClient;
import com.tronix.tickspot.api.parsers.TickSpotClientXmlParser;
import org.simpleframework.xml.*;
import org.simpleframework.xml.core.Persister;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class TickSpotClientXmlParserImpl implements TickSpotClientXmlParser {
    @Root(strict = false)
    public static class client {
        @Element
        public String name;

        @Element
        public int id;
    }

    @Root(strict = false)
    public static class clients {
        @Attribute
        public String type;

        @ElementList(inline = true, entry = "client")
        public List<client> clients;
    }

    @Override
    public List<TickSpotClient> parseClients(String clientsXml) throws XmlPullParserException, IOException {
        Serializer serializer = new Persister();
        clients c = null;
        try {
            c = serializer.read(clients.class, clientsXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
