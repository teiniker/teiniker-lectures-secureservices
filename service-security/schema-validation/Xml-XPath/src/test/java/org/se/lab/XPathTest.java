package org.se.lab;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import junit.framework.TestCase;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class XPathTest extends TestCase
{
    private XPath xpath;
    private DOMPrinter printer;
    
    protected void setUp() 
    {
        xpath = XPathFactory.newInstance().newXPath();
        printer = new DOMPrinter();
    }
     
    
    public void testEvaluateToString() 
        throws XPathExpressionException
    {
        final String EXPR = "/booklist/book/title";
        // Because we don't specify a result type, the XPath expression returns
        // the value as a String object.
        String element = xpath.evaluate(EXPR, new InputSource("src/test/resources/xml/Booklist.xml"));
        System.out.println(EXPR + " = " + element);
    }
    
    
    public void testEvaluateToDOMNode() 
        throws XPathExpressionException
    {
        final String EXPR = "/booklist/book/title";
        Element element = (Element)xpath.evaluate(EXPR,new InputSource("src/test/resources/xml/Booklist.xml"), 
                            XPathConstants.NODE);
        printer.print(element);
    }
    
    
    public void testEvaluateToDOMNodeList() 
        throws XPathExpressionException
    {   
        final String EXPR = "/booklist/book/title";
        NodeList nodes = (NodeList) xpath.evaluate(EXPR, 
                            new InputSource("src/test/resources/xml/Booklist.xml"), 
                            XPathConstants.NODESET);
        for(int i = 0; i< nodes.getLength(); i++)
        {
            printer.print((Element)nodes.item(i));    
            System.out.println();
        }
    }
    
    
    public void testEvaluateToDouble() 
        throws XPathExpressionException
    {
        final String EXPR = "/booklist/book/@price";
        Double price = (Double)xpath.evaluate(EXPR,
                            new InputSource("src/test/resources/xml/Booklist.xml"), 
                            XPathConstants.NUMBER);
        System.out.println("price = " + price);
    }
    
    
    public void testNestedEvaluations() 
        throws XPathExpressionException
    {
        final String EXPR = "/booklist/book";
        
        // First, evaluate an expression to a DOM Node
        Element book = (Element)xpath.evaluate(EXPR, 
                            new InputSource("src/test/resources/xml/Booklist.xml"), 
                            XPathConstants.NODE);
        printer.print(book);
        
        // Second, we can evaluate other XPath expressions against 
        // the resulting DOM Node.
        String title = xpath.evaluate("title/text()", book);
        System.out.println("/booklist/book/title/text() = " + title);
    }
}
