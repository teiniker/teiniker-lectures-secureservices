package org.se.lab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class XmlTest
{
    private final static String xml =
         "<ser:process>" +
         "<arg0 id=\"101\">" +
            "<name>FHJ-20151020-007</name>" +
            "<lines id=\"102\">" +
               "<quantity>2</quantity>" +
               "<product id=\"103\">" +
                  "<description>Effective Java</description>" +
                  "<price>3336</price>" +
               "</product>" +
            "</lines>" +
         "</arg0>" +
      "</ser:process>";
    
    
       @Test
       public void testFindId()
       {        
           Pattern pattern = Pattern.compile("id=\"[0-9]+\"");
           Matcher matcher = pattern.matcher(xml);

           while(matcher.find()) 
           {
               System.out.println("found: " + matcher.start() + " - " + matcher.end() + "  "
                       + xml.substring(matcher.start(), matcher.end()));
           }
       }
       
       @Test
       public void testFindPrice()
       {        
           Pattern pattern = Pattern.compile("<price>[0-9]+</price>");
           Matcher matcher = pattern.matcher(xml);

           while(matcher.find()) 
           {
               System.out.println("found: " + matcher.start() + " - " + matcher.end() + "  "
                       + xml.substring(matcher.start(), matcher.end()));
           }
       }
}
