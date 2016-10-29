SOAP with Attachments API for Java (SAAJ)
---------------------------------------------------------------------

SAAJ is an API we can use to create, read and modify SOAP messages 
using Java. It includes classes and interfaces that model the SOAP 
Envelope, Body, Header, and Fault elements, along with XML namespaces,
elements, attributes, and text nodes.

A "SOAPMessage" contains a "SOAPPart", which represents the SOAP document,
and zero to more "AttachementPart" objects (which represent MIME 
attachments such as GIFs or PDFs).

The "SOAPPart" contains a family of objects that model the SOAP document, 
including the "Envelope", "Header", and Body elements.

  
