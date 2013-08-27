package com.tronix.tickspot.api.parsers;

import com.tronix.tickspot.api.models.TickSpotClient;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public interface TickSpotClientXmlParser {
    public List<TickSpotClient> parseClients(String clientsXml) throws XmlPullParserException, IOException;
}