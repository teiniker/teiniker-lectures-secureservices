package org.se.lab.service;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

public class ValidationHandler implements SOAPHandler<SOAPMessageContext>
{
    private final Logger LOG = Logger.getLogger(ValidationHandler.class);

    @Override
    public boolean handleMessage(SOAPMessageContext context)
    {
        LOG.info("handleMessage()");

        // TODO	
        
        return true;
    }

    
    @Override
    public boolean handleFault(SOAPMessageContext context)
    {
        LOG.info("handleFault()");
        return false;
    }

    @Override
    public void close(MessageContext context)
    {
        LOG.info("close()");
    }

    @Override
    public Set<QName> getHeaders()
    {
        LOG.info("getHeaders()");
        return null;
    }
}
