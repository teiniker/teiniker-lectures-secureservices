package org.se.lab.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

public class ValidationHandler 
    implements SOAPHandler<SOAPMessageContext>
{
    private final Logger LOG = Logger.getLogger(ValidationHandler.class);
    
    @Override
    public boolean handleMessage(SOAPMessageContext context) 
    {
        LOG.info("handleMessage()");
        
        StringBuilder log = new StringBuilder();

        // Check if the message is being invoked on an outgoing or incoming SOAP message
        Boolean outBoundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if(outBoundProperty)
            log.append("SOAP Response:");
        else
            log.append("SOAP Request:");
        
        try 
        {            
            // Get the SOAP Message and grab the headers
            SOAPMessage soapMsg = context.getMessage();
            SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
            SOAPHeader soapHeader = soapEnv.getHeader();
         
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            soapMsg.writeTo(out);
            log.append(out); 
        } 
        catch (SOAPException e) 
        {
            System.err.println(e);
        } 
        catch (IOException e) 
        {
            System.err.println(e);
        }

        LOG.info(log.toString());
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
