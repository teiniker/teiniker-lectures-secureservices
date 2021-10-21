# Example: SOAP API Validation

```
http://localhost:8080/SOAP-EJB-MessageAPI-Validation/OrderService?wsdl
```

## Valid SOAP Request

We send the following request using SoapUI.

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.lab.se.org/">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:process>
         <arg0 id="1">
            <name>ORDER-20151213</name>
            <lines>
               <quantity>5</quantity>
               <product>
                  <description>Effective Java</description>
                  <price>3900</price>
               </product>
            </lines>
         </arg0>
      </ser:process>
   </soapenv:Body>
</soapenv:Envelope>
```
On the Wildfly console output we can see that the send data has been processed.
```
=> Order [id=1, name=ORDER-20151213, lines=[OrderLine [quantity=5, product=Effective Java,3900]]]
```

## Attack: Attribute Injection 

In the following request, we add an additional attribute `hack="true"` to the `process` message.
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.lab.se.org/">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:process>
         <arg0 id="1">
            <name hack="true">ORDER-20151213</name>
            <lines>
               <quantity>5</quantity>
               <product>
                  <description>Effective Java</description>
                  <price>3900</price>
               </product>
            </lines>
         </arg0>
      </ser:process>
   </soapenv:Body>
</soapenv:Envelope>
```
From the Wildfly console output we can see, that this **injected attribute** will be ignored.

```
=> Order [id=1, name=ORDER-20151213, lines=[OrderLine [quantity=5, product=Effective Java,3900]]]
```

## Attack: Overriding Existing Element 

Now we override an existing XML element in the request: `<name>ORDER-666</name>`  
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.lab.se.org/">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:process>
         <arg0 id="1">
            <name>ORDER-20151213</name>
            <lines>
               <quantity>5</quantity>
               <product>
                  <description>Effective Java</description>
                  <price>3900</price>
               </product>
            </lines>
            <name>ORDER-666</name>
         </arg0>
      </ser:process>
   </soapenv:Body>
</soapenv:Envelope>
```
From the Wildfly console output we can see that our injection attack has been successful.
The injected XML element has replaced the original value.
```
=> Order [id=1, name=ORDER-666, lines=[OrderLine [quantity=5, product=Effective Java,3900]]]
```


## Activate Wildfly XML Schema Validation

```
We can activate the XML Schema validation in the `standalone.xml` file:

        <subsystem xmlns="urn:jboss:domain:webservices:2.0">
            <wsdl-host>${jboss.bind.address:127.0.0.1}</wsdl-host>
<!-- BENGIN -->
            <endpoint-config name="Standard-Endpoint-Config">
            	<property name="schema-validation-enabled" value="true"/>
            </endpoint-config>
<!-- END -->
            <endpoint-config name="Recording-Endpoint-Config">
                <pre-handler-chain name="recording-handlers" protocol-bindings="##SOAP11_HTTP ##SOAP11_HTTP_MTOM ##SOAP12_HTTP ##SOAP12_HTTP_MTOM">
                    <handler name="RecordingHandler" class="org.jboss.ws.common.invocation.RecordingServerHandler"/>
                </pre-handler-chain>
            </endpoint-config>
            <client-config name="Standard-Client-Config"/>
        </subsystem>
```

With that configuration we can see that the attacks are not possible anymore:
* Attack: Attribute injection `<lines hack="true">`: 
```
    org.xml.sax.SAXParseException; lineNumber: 7; columnNumber: 13; cvc-complex-type.3.2.2: 
    Attribute 'hack' is not allowed to appear in element 'lines'.
```

* Attack: Overriding existing element `<name>ORDER-666</name>`:
```
    org.xml.sax.SAXParseException; lineNumber: 14; columnNumber: 13; cvc-complex-type.2.4.a: 
	Invalid content was found starting with element 'name'. One of '{lines}' is expected.
```

## References
[Working with WSDL Files ](https://www.soapui.org/docs/soap-and-wsdl/working-with-wsdls/)

*Egon Teiniker, 2020-2021, GPL v3.0*