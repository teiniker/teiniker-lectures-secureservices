package org.se.lab;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

import org.junit.Test;

public class SAAJTest 
{
	@Test
	public void testSOAPMessageCreation() throws SOAPException, IOException
	{
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage();
		
		SOAPHeader header = message.getSOAPHeader();
		SOAPElement key = header.addChildElement("key", "se", "http://www.security.lab.se.org");
		key.addTextNode("ijD8qepbRnIsva0kx0cKRCcYysg=");
				
		SOAPBody body = message.getSOAPBody();
		SOAPElement element = body.addChildElement("book");
		element.addTextNode("Java Programming");
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
        message.writeTo(out);
        
        System.out.println(out);
	}	
}
