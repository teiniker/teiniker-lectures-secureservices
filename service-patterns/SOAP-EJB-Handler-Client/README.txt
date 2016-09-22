How to access the WSDL definition?
-------------------------------------------------------------------------------

Browser URL: http://localhost:8080/SOAP-EJB-Handler/UserService?wsdl

$ curl http://localhost:8080/SOAP-EJB-Handler/UserService?wsdl


How to Generate Client-Side Stubs?
-------------------------------------------------------------------------------

$ pwd
SOAP-EJB-Handler-Client

$ wsimport -verbose -Xnocompile -s src/generated/java -p org.se.lab.client http://localhost:8080/SOAP-EJB-Handler/UserService?wsdl

parsing WSDL...
Generating code...
Compiling code...

$ which wsimport 
/usr/java/jdk1.8.0_60/bin/wsimport

Note that we use the JDK's wsimport tool to generate the client-side classes 
because our JUnit test is running as a simple Java SE application.


How to Configure a Client-Side Handler?
-------------------------------------------------------------------------------

Note that we have to change a generated file!!

src/generated/java/UserResourceEJBService:

@WebServiceClient(name = "UserResourceEJBService", 
				  targetNamespace = "http://service.lab.se.org/", 
				  wsdlLocation = "http://localhost:8080/SOAP-EJB-Handler/UserService?wsdl")
@HandlerChain(file="handler-chain.xml") // <= add this annotation !!!!!!!!!
public class UserResourceEJBService
    extends Service
{
	// ...
}


See also:
https://docs.oracle.com/javaee/5/api/javax/xml/soap/SOAPMessage.html


