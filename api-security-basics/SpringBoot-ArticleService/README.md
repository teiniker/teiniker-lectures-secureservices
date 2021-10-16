# Spring Boot REST Service

In this example, we can see how simple a RESTful service can be implemented with SpringBoot.

## Service Setup

We use Maven to compile and run the service.
```
$ mvn spring-boot:run
```

## Access the REST Service

Find all Articles:
```
$ curl -i http://localhost:8080/articles

```

Find a particular Article:
```    
$ curl -i http://localhost:8080/articles/2

```

Look for an unknown Article:
```    
$ curl -i http://localhost:8080/articles/99

```
   
Insert an Article:
```
$ curl -i -X POST http://localhost:8080/articles -H 'Content-type:application/json' -d '{"description": "Microservices Patterns: With examples in Java", "price": 2550}'

```

Update an Article:
```    
$ curl -i -X PUT http://localhost:8080/articles/2 -H 'Content-type:application/json' -d '{"description": "Effective Java", "price": 9999}'

```

Delete an Article:
```    
$ curl -i -X DELETE http://localhost:8080/articles/3

```


## References

* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

*Egon Teiniker, 2020 - 2021, GPL v3.0*