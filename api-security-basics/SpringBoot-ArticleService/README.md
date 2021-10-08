# Example: SpringBoot ArticleService 


## Using the Service

### Find Articles

```
$ curl -i localhost:8080/articles

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 07 Oct 2021 13:38:59 GMT

[
    {"id":1,"description":"Design Patterns","price":4295},
    {"id":2,"description":"Effective Java","price":3336}
]
```

```
$ curl -i localhost:8080/articles/2

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 07 Oct 2021 13:39:04 GMT

{"id":2,"description":"Effective Java","price":3336}
```

```
$ curl -i localhost:8080/articles/99

HTTP/1.1 404
Content-Type: text/plain;charset=UTF-8
Content-Length: 26
Date: Thu, 07 Oct 2021 14:14:49 GMT

Could not find employee 99
```

### Insert an Article
```
$ curl -i -X POST localhost:8080/articles -H 'Content-type:application/json' -d '{"description": "Microservices Patterns: With examples in Java", "price": 2550}'

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 07 Oct 2021 14:17:36 GMT

{"id":3,"description":"Microservices Patterns: With examples in Java","price":2550}
```

### Update an Article
```
$ curl -i -X PUT localhost:8080/articles/2 -H 'Content-type:application/json' -d '{"description": "Effective Java", "price": 9999}'

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 07 Oct 2021 14:20:22 GMT

{"id":2,"description":"Effective Java","price":9999}
```

### Delete an Article
```
$ curl -i -X DELETE localhost:8080/articles/3

HTTP/1.1 200
Content-Length: 0
Date: Thu, 07 Oct 2021 14:23:30 GMT
```

```
$ curl -i localhost:8080/articles/3

HTTP/1.1 404
Content-Type: text/plain;charset=UTF-8
Content-Length: 25
Date: Thu, 07 Oct 2021 14:24:05 GMT

Could not find employee 3
```



## References

[Building REST services with Spring](https://spring.io/guides/tutorials/rest/)