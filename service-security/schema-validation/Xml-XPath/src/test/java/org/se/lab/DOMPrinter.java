package org.se.lab;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class DOMPrinter
{
    
    public void print(Node node)
    {
        switch(node.getNodeType())
        {
            case Node.DOCUMENT_NODE:
                print((Document)node);
                break;
                
            case Node.DOCUMENT_TYPE_NODE:
                print((DocumentType)node);
                break;       
                
            case Node.ELEMENT_NODE:
                print((Element)node);
                break;
         
            case Node.TEXT_NODE:
                print((Text)node);
                break; 

            case Node.CDATA_SECTION_NODE:
                print((CDATASection)node);
                break; 
                
            case Node.COMMENT_NODE:
                print((Comment)node);
                break;
    
            case Node.PROCESSING_INSTRUCTION_NODE:
                print((ProcessingInstruction)node);
                break;
                
            case Node.ENTITY_REFERENCE_NODE:
                print((EntityReference)node);
                break;       
        }
    }

    
    public void print(Document document)
    {
        System.out.println("DOCUMENT_NODE: ");
        System.out.println("  Version = " + document.getXmlVersion());
        System.out.println("  Encoding = " + document.getXmlEncoding());
        System.out.println("  Standalone = " + document.getXmlStandalone());                

        // Handle all child nodes
        NodeList nodes = document.getChildNodes();
        for(int i = 0; i< nodes.getLength();i++)
        {
            print(nodes.item(i));
        }
    }

    
    protected void print(Element element)
    {
        System.out.println("ELEMENT_NODE: ");
        System.out.println("  NodeName = " + element.getNodeName());
        System.out.println("  LocalName = " + element.getLocalName());
        System.out.println("  NamespaceURI = " + element.getNamespaceURI());
        System.out.println("  Prefix = " + element.getPrefix());
        
        NamedNodeMap attributes = element.getAttributes();
        for(int i=0; i<attributes.getLength(); i++)
        {
            Node attr = attributes.item(i);
            System.out.println("  Attribute: ");
            System.out.println("    NodeName = " +attr.getNodeName());
            System.out.println("    LocalName = " +attr.getLocalName());
            System.out.println("    NamespaceURI = " +attr.getNamespaceURI());
            System.out.println("    Prefix = " + attr.getPrefix());
            System.out.println("    Value = \"" + attr.getNodeValue() + "\"");
        }
                
        NodeList children = element.getChildNodes();
        for(int i=0; i< children.getLength(); i++)
        {
            print(children.item(i));
        }        
    }
    
    
    protected void print(Text text)
    {
        System.out.println("TEXT_NODE: ");
        System.out.println("  Value = \"" + text.getNodeValue().trim() + "\"");        
    }

    
    protected void print(CDATASection cdata)
    {
        System.out.println("CDATA_SECTION_NODE: ");
        System.out.println("  Value = \"" + cdata.getNodeValue() + "\"");        
    }

    
    protected void print(Comment comment)
    {
        System.out.println("COMMENT_NODE: ");
        System.out.println("  Value = \"" + comment.getNodeValue().trim() + "\"");        
    }

    
    protected void print(ProcessingInstruction instr)
    {
        System.out.println("PROCESSING_INSTRUCTION_NODE: ");
        System.out.println("  Name = " + instr.getNodeName());        
        System.out.println("  Value = " + instr.getNodeValue());
    }
    
    
    protected void print(DocumentType node)
    {
        System.out.println("DOCUMENT_TYPE_NODE: ");
        System.out.println("  Name = " + node.getName());
        System.out.println("  PublicId = " + node.getPublicId());        
        System.out.println("  SystemId = " + node.getSystemId());
        System.out.println("  InternalSubset = " + node.getInternalSubset());
    }

    
    protected void print(EntityReference node)
    {
        System.out.println("ENTITY_REFERENCE_NODE: ");
        System.out.println("  Name = " + node.getNodeName());
    }
}
