package org.se.lab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class ItemTest
{
	private JAXBContext context;

	@Before
	public void setup() throws JAXBException
	{
		context = JAXBContext.newInstance("org.se.lab");
	}
	
	/*
	 * Tag Injection Attack
	 * 
	 * When the XML document is parsed, it interprets the second element as overriding the first...
	 */
	@Test
	public void testTagInjectionAttack() throws SAXException, JAXBException, FileNotFoundException
	{
		Source src = new StreamSource(new FileReader(new File("src/test/resources/xml", "item-tag-injection.xml")));
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<Item> element = unmarshaller.unmarshal(src, Item.class);
		Item item = element.getValue();

		Assert.assertEquals("Widget", item.getDescription());
		Assert.assertEquals(BigInteger.valueOf(1), item.getQuantity());
		Assert.assertEquals(new BigDecimal("500.0"), item.getPrice());
	}

	
	@Test(expected = UnmarshalException.class)
	public void testTagInjectionWithValidation() throws SAXException, JAXBException, FileNotFoundException
	{
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		Schema xsd = schemaFactory.newSchema(new File("src/main/resources/xsd", "session.xsd"));
		
		Source src = new StreamSource(new FileReader(new File("src/test/resources/xml", "item-tag-injection.xml")));
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setSchema(xsd);  // validate XML against XSD
		JAXBElement<Item> element = unmarshaller.unmarshal(src, Item.class);
		element.getValue();
	}
}
