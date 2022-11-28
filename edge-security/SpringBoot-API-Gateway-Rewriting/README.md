# Spring Cloud Gateway - URL Rewriting

**URL Rewriting** is about how to **define a route that rewrites the incoming URL before sending it to the backend**.

To create a **configuration-based rewrite**, we just need to add a few properties to the application's configuration.
* Firstly, we have the route's `id`, which is just its identifiers. 
* Next, we have the backend URI given by the `uri` property. Notice that only hostname/port are considered, as the 
  final path comes from the rewrite logic.
* Finally, the `filters` property has the actual rewrite logic. 
  The `RewritePath` filter takes two arguments: a regular expression and a replacement string.
  The filter's implementation works by simply executing the `replaceAll()` method on the request's URI, 
  using the provided parameters as arguments.

_Example_: `/foo/bar` -> `/bar`
```
RewritePath=/foo/(?<segment>.*), /$\{segment}
```
For a request path of `/foo/bar`, this will set the path to `/bar` before making the downstream request.
Notice the `$\` which is replaced with `$` because of the YAML spec.


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
$ cd SpringBoot-API-Gateway-Rewriting
$ mvn spring-boot:run

$ $ curl -v http://localhost:8080/v1/articles
[{"id":1,"description":"Design Patterns","price":4295},{"id":2,"description":"Effective Java","price":3336}]
```



## References

* [URL Rewriting With Spring Cloud Gateway](https://www.baeldung.com/spring-cloud-gateway-url-rewriting)
* [Spring Cloud Gateway - Part 2](https://thetechstack.net/spring-cloud-gateway-part-2)

*Egon Teiniker, 2016-2022, GPL v3.0*