Apache CXF: An Open-Source Services Framework
-------------------------------------------------------------------------------
http://cxf.apache.org/

CXF helps you build and develop services using frontend programming APIs, like 
JAX-WS and JAX-RS.

JSR Support:
	JAX-WS - Java API for XML-Based Web Services (JAX-WS) 2.0 - JSR-224
	Web Services Metadata for the Java Platform - JSR-181
	JAX-RS - The Java API for RESTful Web Services - JSR-311
	SAAJ - SOAP with Attachments API for Java (SAAJ) - JSR-67
	
	
JAX-RS Client API
-------------------------------------------------------------------------------
https://docs.oracle.com/javaee/7/api/javax/ws/rs/client/package-summary.html
	
The JAX-RS client API is a Java based API used to access Web resources. It 
provides a higher-level abstraction compared to a plain HTTP communication API 
as well as integration with the JAX-RS extension providers.
	
A Web resource can be accessed using a fluent API in which method invocations 
are chained to configure and ultimately submit an HTTP request. 

Example: The following example gets a text/plain representation of the resource 
		 identified by "http://example.org/hello":
   
   Client client = ClientBuilder.newClient();
   Response res = client.target("http://example.org/hello").request("text/plain").get();
 
Conceptually, the steps required to submit a request are the following:
1) obtain an Client instance
2) create a WebTarget pointing at a Web resource
3) build a request
4) submit a request to directly retrieve a response or get a prepared Invocation 
	for later submission	
	
	
Individual Web resources are in the JAX-RS Client API represented as resource 
targets. Each WebTarget instance is bound to a concrete URI, 
	e.g. "http://example.org/messages/123", 
or a URI template, 
	e.g. "http://example.org/messages/{id}". 

That way a single target can either point at a particular resource or represent a 
larger group of resources (that e.g. share a common configuration) from which 
concrete resources can be later derived.
 	