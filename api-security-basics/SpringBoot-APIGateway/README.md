# API Gateway

The API gateway is an important piece of infrastructure in our microservice architecture,
since it plays a critical role that helps us clearly separate the functional requirements 
from nonfunctional ones.

Microservices are behind a set of APIs that is exposed to the outside world via an API 
gateway.
The API gateway is the entry point to the microservice deployment, which screens all 
incoming messages for security and other QoS features.

One key aspect of microservices best practice is the 
**single responsibility principle (SRP)**.
Each microservice should be performing only one particular function.
An API gateway helps in **decoupling security from a microservice**.

## API Gateway Setup
We use **Zuul** which is an open-source proxy server build by Netflix, acting as the entry point for
all of the company's backend streaming applications.

We configure the API gateway to use port `9090` and to forward API requests to another service running on port `8080`.
All configurations can be done in the **application.properties** file.
```
zuul.routes.api.url=http://localhost:8080
zuul.sensitiveHeaders=
ribbon.eureka.enabled=false
server.port=9090
```

First, we start the `ArticlesService` and then the API gateway.

```
$ cd SpringBoot-ArticleService
$ mvn spring-boot:run
```

```
$ cd SpringBoot-APIGateway
$ mvn spring-boot:run
```

## Accessing the API Gateway

We send a request to the API gateway which forwards the request to the `ArticleService` implementation.

```
$ curl -i http://localhost:9090/api/articles/1

HTTP/1.1 200 
Date: Sat, 16 Oct 2021 09:12:15 GMT
Keep-Alive: timeout=60
Content-Type: application/json
Transfer-Encoding: chunked

{"id":1,"description":"Design Patterns","price":4295}
```

Unfortunately, we can also bypass the API gateway and directly access the `ArticleService`.
```
$ curl -i http://localhost:8080/articles/1

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 16 Oct 2021 09:26:58 GMT

{"id":1,"description":"Design Patterns","price":4295}
```

Thus, we have to create a more secure solution (see: Edge Security). 



## References
* Prabath Siriwardena, Nuwan Dias. **Microservices Security in Action**. Manning, 2020

*Egon Teiniker, 2020 - 2021, GPL v3.0*