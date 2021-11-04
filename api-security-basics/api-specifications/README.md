# API Design Best Practices

We should design the APIs first and implemented services later.

## Use Nouns and Not Verbs When Naming a Resource in the Endpoint Path
HTTP methods use verbs.
Therefore, it would be redundant to use verbs, and it would make our call look like an RPC endpoint.

_Example_: GitHub license API to retrieve the available licenses
```    
GET /licenses 
```
If we use verbs for this endpoint, then it will be `GET /getlicenses`.
It will still work, but semantically, it doesn't follow REST because it conveys the processing
instruction rather than state transfer. Therefore, we only use resource names.

GitHub's public API only offers read operations on the `licenses` resource, 
out of all the CRUD operations.

If we need to design the rest of the operations, their paths should look like following:
* `POST /licenses`: This is for creating a new license.
* `PUT /licenses/{license_key}`: This is for partial updates. Here, the path has a parameter
  (that is, an identifier), which makes the path dynamic. The license key is a unique value
  in the license collection and is being used as an identifier.
  Each license will have a unique key. This call should make the update in the given license.
  Note that `PATCH` could also be used.

* `DELETE /licenses/{license_key}`: This is for removing license information.
  We can try this with any license that we receive in the response of the `GET /licenses` call.

## Use the Plural Form for Naming the Collection Resource in the Endpoint Path

If we observe the GitHub `license` API, we might find that a resource name is given in the plural form.
It is a good practice to use the plural form if the resource represents a collection.
A `GET` call returns the collection of `licenses`.


## Status codes
Please follow the guidelines of HTTP methods and status codes.


## Use Hypermedia (HATEOAS)
Hypermedia (that is, links to other resources) makes the REST client's job easier.

There are two advantages if we provide explicit URL links in a response:

* The REST client is not required to construct the REST URLs on their own.

* Any upgrade in the endpoint path will be taken care of automatically and this makes
  upgrades easier for clients and developers.


## Always Version APIs
The versioning of APIs is key for future upgrades.
Over time, APIs keep changing, and we may have customers who are still using an older
version. Therefore, we need to support multiple versions of APIs.

There are different ways we can version your APIs:
* **Using headers**: The GitHub API uses this approach.
We can add an `Accept` header that tells you which API version should serve the request.
  
_Example_:
```
  Accept: application/vnd.github.v3+json
```

This approach gives you the advantage of **setting the default version**.
If there is no `Accept` header, it should lead to the default version.
However, if a REST client that uses a versioning header is not changed after a recent upgrade
of APIs, it may lead to a functionality break.
Therefore, it is recommended that you use a versioning header.

* **Using an endpoint path**: We add a version in the endpoint path itself.
```
Example: https://demo.app/api/v1/persons
```

`v1` denotes that version 1 is being added to the path itself.
Here, **we cannot set default** versioning out of the box.
However, we can overcome this limitation by using other methods, such as request forwarding.
Clients always use the intended versions of the APIs in this approach.


## Documentation
Documentation should be easily accessible and up to date with the latest implementation
with their respective versioning. It is always good to provide sample code and examples.
It makes the developer's integration job easier.

A **change log or a release log** should list all of the impacted libraries, and if some APIs are
deprecated, then replacement APIs or workarounds should be elaborated on inside the documentation.


## References

* Sourabh Sharma. **Modern API Development with Spring and Spring Boot: Design highly scalable and maintainable APIs with
  REST, gRPC, GraphQL, and the reactive paradigm**. Packt Publishing, 2021
  * Chapter: Best practices for designing REST APIs
  
*Egon Teiniker, 2020-2021, GPL v3.0*