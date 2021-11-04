# Hypermedia as the Engine of Application State (HATEOAS)

HATEOAS is a very important concept of REST. 
It is one of the concepts that **differentiate REST from RPC**.

With HATEOAS, RESTful web services provide information dynamically through hypermedia.
Hypermedia is a part of the content that you receive from a REST call response.
This hypermedia content contains links to different types of media such as text, images, and videos.

**Hypermedia links can be contained either in HTTP headers or the response body**.

_Example_: GitHub API
If we look at the responses of GitHub APIs, we will also find other resource-related links with keys
that have a postfix of `url`.
```
$ curl -k https://api.github.com/users/teiniker
{
"login": "teiniker",
"id": 14111058,
"node_id": "MDQ6VXNlcjE0MTExMDU4",
"avatar_url": "https://avatars.githubusercontent.com/u/14111058?v=4",
"gravatar_id": "",
"url": "https://api.github.com/users/teiniker",
"html_url": "https://github.com/teiniker",
"followers_url": "https://api.github.com/users/teiniker/followers",
"following_url": "https://api.github.com/users/teiniker/following{/other_user}",
"gists_url": "https://api.github.com/users/teiniker/gists{/gist_id}",
"starred_url": "https://api.github.com/users/teiniker/starred{/owner}{/repo}",
"subscriptions_url": "https://api.github.com/users/teiniker/subscriptions",
"organizations_url": "https://api.github.com/users/teiniker/orgs",
"repos_url": "https://api.github.com/users/teiniker/repos",
"events_url": "https://api.github.com/users/teiniker/events{/privacy}",
"received_events_url": "https://api.github.com/users/teiniker/received_events",
"type": "User",
...
}
```

Additionally, we can find many URLs in the response body, such as "repos_url".
We can follow that link to get a list of assoicated repositories.
```
$ curl -k https://api.github.com/users/teiniker/repos
[
...
{
"id": 68909117,
"node_id": "MDEwOlJlcG9zaXRvcnk2ODkwOTExNw==",
"name": "teiniker-lectures-secureservices",
"full_name": "teiniker/teiniker-lectures-secureservices",
"private": false,
"owner": {
"login": "teiniker",
"id": 14111058,
...
},
"html_url": "https://github.com/teiniker/teiniker-lectures-secureservices",
"description": null,
"fork": false,
"url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices",
"forks_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/forks",
"keys_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/keys{/key_id}",
"collaborators_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/collaborators{/collaborator}",
"teams_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/teams",
"hooks_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/hooks",
"issue_events_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/issues/events{/number}",
"events_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/events",
"assignees_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/assignees{/user}",
"branches_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/branches{/branch}",
"tags_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/tags",
...
"created_at": "2016-09-22T10:07:23Z",
"updated_at": "2021-11-03T07:57:15Z",
"pushed_at": "2021-11-03T07:57:12Z",
"git_url": "git://github.com/teiniker/teiniker-lectures-secureservices.git",
...
}
]
```

From there, we can navigate to a particular repository and from there to a list of its tags.
```
$ curl -k https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/tags
[
{
"name": "2020WS",
"zipball_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/zipball/refs/tags/2020WS",
"tarball_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/tarball/refs/tags/2020WS",
"commit": {
"sha": "4ff466f1819612134e9c78d51282e2c76b60012a",
"url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/commits/4ff466f1819612134e9c78d51282e2c76b60012a"
},
"node_id": "MDM6UmVmNjg5MDkxMTc6cmVmcy90YWdzLzIwMjBXUw=="
},
{
"name": "2016WS",
"zipball_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/zipball/refs/tags/2016WS",
"tarball_url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/tarball/refs/tags/2016WS",
"commit": {
"sha": "be1ebc8aeb920bacfe47205b8280d5617d1b5eef",
"url": "https://api.github.com/repos/teiniker/teiniker-lectures-secureservices/commits/be1ebc8aeb920bacfe47205b8280d5617d1b5eef"
},
"node_id": "MDM6UmVmNjg5MDkxMTc6cmVmcy90YWdzLzIwMTZXUw=="
}
]
```

REST clients can interact with RESTful web services without having any specific knowledge of how
to interact with the server. We just call any static REST API endpoint and we will receive the
dynamic links as a part of the response to interact further.

REST allows clients to dynamically navigate to the appropriate resource by traversing the links.
It empowers machines, as REST clients can navigate to different resources in a similar way to how
humans look at a web page and click on any link.
The REST client makes use of these links to navigate.


## Spring Boot HATEOAS

In 
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

## Implementation

Spring Boot 
```Java
```


## References

* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

* Sourabh Sharma. **Modern API Development with Spring and Spring Boot:
  Design highly scalable and maintainable APIs with REST, gRPC, GraphQL, and the reactive paradigm**.  
  Packt Publishing, 2021



*Egon Teiniker, 2020-2021, GPL v3.0*