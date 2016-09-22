package org.se.lab;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import junit.framework.TestCase;

import org.xml.sax.InputSource;


public class XPathNamespacesTest extends TestCase
{
    private XPath xpath;
    
    protected void setUp() 
    {
        xpath = XPathFactory.newInstance().newXPath();
        
        // Register a NamespaceContext implementation object
        // to the XPath object. 
        // Note that an explicit NamespaceContext object is
        // required to support XML namespaces in XPath expressions!
        xpath.setNamespaceContext(new SimpleNamespaceContext());        
    }
     
    
    public void testNamespacePrefix() 
        throws XPathExpressionException
    {        
        final String EXPR = "/fhj:booklist/fhj:book/fhj:title";
        String element = xpath.evaluate(EXPR, 
                            new InputSource("src/test/resources/xml/BooklistNamespacePrefix.xml"));
        System.out.println(EXPR + " = " + element);
    }

    
    public void testDefaultNamespace() 
        throws XPathExpressionException
    {
        // Because XPath doesn't support default namespaces, we have to
        // use the 'default' prefix (which is mapped in the NamespaceContext
        // implementation).
        final String EXPR = "/fhj:booklist/default:book/default:title";
        String element = xpath.evaluate(EXPR, 
                            new InputSource("src/test/resources/xml/BooklistNamespacePrefix.xml"));
        System.out.println(EXPR + " = " + element);
    }
}
