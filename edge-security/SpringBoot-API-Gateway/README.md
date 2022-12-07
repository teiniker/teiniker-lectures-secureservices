# Spring Cloud Gateway

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

$ curl -v http://localhost:8080/articles
[{"id":1,"description":"Design Patterns","price":4295},{"id":2,"description":"Effective Java","price":3336}]
```



## References
* [YouTube: Use Spring Cloud Gateway to drive traffic to your APIs](https://youtu.be/wYk0JrNdb8g)
* [Spring Cloud Tutorial - Spring Cloud Gateway Hello World Example](https://www.javainuse.com/spring/cloud-gateway)
* [Spring Cloud Gateway](https://cloud.spring.io/spring-cloud-gateway/reference/html)

*Egon Teiniker, 2016-2022, GPL v3.0*
