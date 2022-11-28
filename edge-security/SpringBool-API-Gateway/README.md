# Spring Cloud Gateway

**Zuul is a blocking API Gateway**.
A blocking gateway makes use of as many threads as the number of incoming requests.
So this approach is more resource intensive. If no threads are available to process incoming
request then the request has to wait in queue.

**Spring Cloud Gateway is a non-blocking API gateway**. 
When using a non-blocking API gateway, a thread is always available to process the incoming request. 
These request are then processed asynchronously in the background and once completed the response is 
returned. So no incoming request never gets blocked when using Spring Cloud Gateway.

## Setup

Set up the microservice: 
```
$ cd SpringBoot-ArticleService
$ mvn spring-boot:run

$ curl -i http://localhost:9090/articles
[{"id":1,"description":"Design Patterns","price":4295},{"id":2,"description":"Effective Java","price":3336}]
```

Set up the API gateway:
```
$ cd SpringBoot-API-Gateway
$ mvn spring-boot:run

$ curl -v http://localhost:8080/articles/
[{"id":1,"description":"Design Patterns","price":4295},{"id":2,"description":"Effective Java","price":3336}]
```



## References

* [Spring Cloud Tutorial - Spring Cloud Gateway Hello World Example](https://www.javainuse.com/spring/cloud-gateway)
* [Spring Cloud Gateway](https://cloud.spring.io/spring-cloud-gateway/reference/html)

*Egon Teiniker, 2016-2022, GPL v3.0*