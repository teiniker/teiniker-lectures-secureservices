package org.se.lab;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;


public class SimpleNamespaceContext
    implements NamespaceContext
{
    /**
     * Get NamespaceURI bound to a prefix in the current scope.
     */
    public String getNamespaceURI(String prefix)
    {
        System.out.println("SimpleNamespaceContext.getNamespaceURI(\"" + prefix + "\")");
        
        if(prefix == null)
            throw new NullPointerException("Prefix is null!");        
        else if(prefix.equals("fhj")) 
            return "http://fhj.at";
        else if(prefix.equals("default"))
            return "http://sf.net";
        else    
            return XMLConstants.NULL_NS_URI;
    }

    
    /**
     * Get prefix bound to Namespace URI in the current scope.
     */
    public String getPrefix(String namespaceURI)
    {
        throw new UnsupportedOperationException();
    }

    
    /**
     * Get all prefixes bound to a Namespace URI in the current
     * scope.
     */
    public Iterator getPrefixes(String namespaceURI)
    {
        throw new UnsupportedOperationException();
    }
}
