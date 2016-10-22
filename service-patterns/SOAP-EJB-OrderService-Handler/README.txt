How to access the WSDL definition?
-------------------------------------------------------------------------------

URL: http://localhost:8080/SOAP-EJB-OrderService-Handler/OrderService?wsdl

1. SOAP Message 
--------------------------

Order.id = 101
Order.name = "FHJ-20151020-007"	     
	Line.id = 102
	Line.quantity = 2
		Product.id= 103     
		Product.description = "Effective Java"
		Product.price= 3336
	Line.id = 104
	Line.quantity = 4
		Product.id = 105
		Product.description = "Design Patterns"	
		Product.price = 5280
		
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.lab.se.org/">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:process>
         <arg0 id="101">
            <name>FHJ-20151020-007</name>
            <lines id="102">
               <quantity>2</quantity>
               <product id="103">
                  <description>Effective Java</description>
                  <price>3336</price>
               </product>
            </lines>
            <lines id="104">
               <quantity>4</quantity>
               <product id="105">
                  <description>Design Patterns</description>
                  <price>5280</price>
               </product>
            </lines>            
         </arg0>
      </ser:process>
   </soapenv:Body>
</soapenv:Envelope>

process: 
	Order [id=101, name=FHJ-20151020-007, 
		lines=[
			OrderLine [id=102,quantity=2, product=Product [id=103,description=Effective Java, price=3336]], 
			OrderLine [id=104,quantity=4, product=Product [id=105,description=Design Patterns, price=5280]]]]
			
2. Logging Handler
-----------------------------

Extend the handleMessage(SOAPMessageContext context) method to validate the 
SOAP request: 
	Constraint: id should be unique
	
	Constraint: price should be positive

If the SOAP request is valid, return true and false in the other case.
You can use the XmlTest to find some useful code...	

Record the SOAP messages that you use for testing (add it to this TODO.txt file).

	