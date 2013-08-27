package com.tronix.tickspot.api.parsers.impl;

import com.tronix.tickspot.api.models.TickSpotClient;
import com.tronix.tickspot.api.models.TickSpotClientsProjectsTasks;
import com.tronix.tickspot.api.parsers.TickSpotClientXmlParser;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class TickSpotClientXmlParserImpl implements TickSpotClientXmlParser {
    @Override
    public List<TickSpotClient> parseClients(String clientsXml) throws XmlPullParserException, IOException {
        Serializer serializer = new Persister();
        TickSpotClientsProjectsTasks clientsProjectsTasks = null;
        try {
            clientsProjectsTasks = serializer.read(TickSpotClientsProjectsTasks.class, clientsXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientsProjectsTasks.getClients();
    }
}
