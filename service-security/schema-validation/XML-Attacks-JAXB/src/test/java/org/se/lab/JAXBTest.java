package org.se.lab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class JAXBTest
{
	private JAXBContext context;

	@Before
	public void setup() throws JAXBException
	{
		context = JAXBContext.newInstance("org.se.lab");
	}
	
	
	@Test
	public void testJAXBWithValidation() throws SAXException, JAXBException, FileNotFoundException
	{
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		Schema xsd = schemaFactory.newSchema(new File("src/main/resources/xsd", "session.xsd"));
		
		Source src = new StreamSource(new FileReader(new File("src/test/resources/xml", "session.xml")));
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setSchema(xsd);  // validate XML against XSD
		JAXBElement<SessionRootType> element = unmarshaller.unmarshal(src, SessionRootType.class);
		SessionRootType root = element.getValue();

		Assert.assertEquals(2, root.getSessions().size());
		
		SessionType s1 = root.getSessions().get(0);
		Assert.assertEquals("one", s1.getId());

		SessionType s2 = root.getSessions().get(1);
		Assert.assertEquals("two", s2.getId());
	}


	@Test
	public void testJAXBWithoutValidation() throws SAXException, JAXBException, FileNotFoundException
	{
		Source src = new StreamSource(new FileReader(new File("src/test/resources/xml", "modified-session.xml")));
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<SessionRootType> element = unmarshaller.unmarshal(src, SessionRootType.class);
		SessionRootType root = element.getValue();

		Assert.assertEquals(2, root.getSessions().size());
		
		SessionType s1 = root.getSessions().get(0);
		Assert.assertEquals("one", s1.getId());

		SessionType s2 = root.getSessions().get(1);
		Assert.assertEquals("two", s2.getId());
	}

}
