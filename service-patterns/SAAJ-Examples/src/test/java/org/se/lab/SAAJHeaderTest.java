package org.se.lab;

import java.io.IOException;
import java.util.Iterator;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SAAJHeaderTest 
{
	private SOAPMessage message;
	
	@Before
	public void setup() throws SOAPException, IOException
	{
		MessageFactory factory = MessageFactory.newInstance();
		message = factory.createMessage();
		
		SOAPHeader header = message.getSOAPHeader();
		SOAPElement key = header.addChildElement("key", "se", "http://www.security.lab.se.org");
				
		SOAPElement v = key.addChildElement("value");
		v.setTextContent("ijD8qepbRnIsva0kx0cKRCcYysg=");
		
		SOAPBody body = message.getSOAPBody();
		SOAPElement element = body.addChildElement("book");
		element.addTextNode("Java Programming");
	}
	
	
	@Test
	public void testReadHeaderElements() throws SOAPException, IOException
	{
		SOAPHeader soapHeader = message.getSOAPHeader();
        
        Iterator<?> it = soapHeader.getChildElements();
        while(it.hasNext())
        {
        	SOAPHeaderElement element = (SOAPHeaderElement)it.next();
        	System.out.println("SOAPHeaderElement: " + element);            	
        }
	}	

	@Test
	public void testReadNestedHeaderElement() throws SOAPException, IOException
	{
		SOAPHeader soapHeader = message.getSOAPHeader();
        
        Iterator<?> it = soapHeader.getChildElements();
        SOAPElement key = (SOAPElement)it.next();
        Iterator<?> it2 = key.getChildElements();
        SOAPElement value = (SOAPElement)it2.next();
        
        System.out.println(value.getTextContent());
	}	

	@Test
	public void testNestedHeaderElementUsingDOM() throws SOAPException, IOException
	{
		SOAPHeader soapHeader = message.getSOAPHeader();
        
        NodeList list = soapHeader.getElementsByTagNameNS("*", "value");
        Node node = list.item(0);
        
        System.out.println(node.getTextContent());
	}	
}
