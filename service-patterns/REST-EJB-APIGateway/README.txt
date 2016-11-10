How to access the REST resource?
-------------------------------------------------------------------------------

URL: http://localhost:8080/REST-EJB-APIGateway/v1/articles


JAX-RS Client API
---------------------------------------------------------------------
https://jersey.java.net/documentation/latest/client.html

A fluent Java based API for communication with RESTful Web services. 
This standard API that is part of Java EE 7 is designed to make it 
easy to consume a Web service exposed via HTTP protocol and enables 
developers to concisely and efficiently implement portable client-side 
solutions that leverage existing and well established client-side HTTP 
connector implementations.



find single article
---------------------------------------------------------------------
GET http://localhost:8080/REST-EJB-APIGateway/v1/articles/1 HTTP/1.1
Accept-Encoding: gzip,deflate
Accept: application/json
Host: localhost:8080
Connection: Keep-Alive
User-Agent: Apache-HttpClient/4.1.1 (java 1.5)

HTTP/1.1 200 OK
Connection: keep-alive
X-Powered-By: Undertow/1
Server: WildFly/10
Content-Type: application/json
Content-Length: 53
Date: Thu, 10 Nov 2016 14:36:31 GMT

{"id":1,"description":"Design Patterns","price":4295}


find all articles
---------------------------------------------------------------------
GET http://localhost:8080/REST-EJB-APIGateway/v1/articles HTTP/1.1
Accept-Encoding: gzip,deflate
Accept: application/json
Host: localhost:8080
Connection: Keep-Alive
User-Agent: Apache-HttpClient/4.1.1 (java 1.5)


HTTP/1.1 200 OK
Connection: keep-alive
X-Powered-By: Undertow/1
Server: WildFly/10
Content-Type: application/json
Content-Length: 178
Date: Thu, 10 Nov 2016 14:38:36 GMT

[{"id":1,"description":"Design Patterns","price":4295},{"id":2,"description":"Effective Java (2nd Edition)","price":3336},{"id":100,"description":"Design Patterns","price":4295}]


Removed all other methods
---------------------------------------------------------------------

POST http://localhost:8080/REST-EJB-APIGateway/v1/articles HTTP/1.1
Accept-Encoding: gzip,deflate
Content-Type: application/xml
Accept: application/json
Content-Length: 79
Host: localhost:8080
Connection: Keep-Alive
User-Agent: Apache-HttpClient/4.1.1 (java 1.5)

<article price="4295">
   <description>Design Patterns</description>
</article>


HTTP/1.1 405 Method Not Allowed
Allow: HEAD, GET, OPTIONS
Connection: keep-alive
X-Powered-By: Undertow/1
Server: WildFly/10
Content-Length: 0
Date: Thu, 10 Nov 2016 14:42:00 GMT


DELETE http://localhost:8080/REST-EJB-APIGateway/v1/articles/1 HTTP/1.1
Accept-Encoding: gzip,deflate
Content-Type: application/json
Accept: application/json
Content-Length: 0
Host: localhost:8080
Connection: Keep-Alive
User-Agent: Apache-HttpClient/4.1.1 (java 1.5)

HTTP/1.1 405 Method Not Allowed
Allow: HEAD, GET, OPTIONS
Connection: keep-alive
X-Powered-By: Undertow/1
Server: WildFly/10
Content-Length: 0
Date: Thu, 10 Nov 2016 14:42:41 GMT


PUT http://localhost:8080/REST-EJB-APIGateway/v1/articles/1 HTTP/1.1
Accept-Encoding: gzip,deflate
Content-Type: application/xml
Accept: application/json
Content-Length: 79
Host: localhost:8080
Connection: Keep-Alive
User-Agent: Apache-HttpClient/4.1.1 (java 1.5)

<article price="4295">
   <description>Design Patterns</description>
</article>

HTTP/1.1 405 Method Not Allowed
Allow: HEAD, GET, OPTIONS
Connection: keep-alive
X-Powered-By: Undertow/1
Server: WildFly/10
Content-Length: 0
Date: Thu, 10 Nov 2016 14:43:35 GMT
