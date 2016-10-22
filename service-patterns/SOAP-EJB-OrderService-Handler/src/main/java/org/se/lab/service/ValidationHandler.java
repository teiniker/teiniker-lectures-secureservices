package org.se.lab.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
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

        // Check if the message is being invoked on an outgoing or incoming SOAP message
        Boolean outBoundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if(outBoundProperty)
            return true; // response
        
        try
        {            
        	StringBuilder log = new StringBuilder();
            SOAPMessage soapMsg = context.getMessage();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            soapMsg.writeTo(out);
            String xml = out.toString();
            log.append(xml);
            LOG.info(log.toString());

            return isValidMessage(xml);
        }
        catch (SOAPException | IOException e) 
        {
            LOG.error("Can't process SOAP message!", e);
        } 
        return false;
    }

    private boolean isValidMessage(String xml)
    {
        List<String> ids = new ArrayList<>();
        Pattern pattern = Pattern.compile("id=\"[0-9]+\"");
        Matcher matcher = pattern.matcher(xml);

        while(matcher.find()) 
        {
            String id = xml.substring(matcher.start(), matcher.end());
            LOG.info("   found: " + id);
            if(ids.contains(id))
                return false;
            else
            {
                ids.add(id);
            }
        }
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
