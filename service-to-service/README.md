# Securing Communication Between Microservices and the API Gateway 

We have to consider what happens if someone accesses the microservice directly,
**bypassing the API gateway** layer.

First and formost, we need to make sure that our microservice isn't directly
exposed to external clients. We need to make sure that it sits behind an organisation's
**firewall**.
No external client get access to our microservice unless they come in via the API gateway.

We need to build a mechanism in which the microservice rejects any request comming from
clients other than the API gateway.
The standard way is to enable **mTLS** between the API gateway and the microservice.

Note that **mTLS verification happens at the transport layer** of the microservice and doesn't
propagate up to the application layer.
Developers don't have to write any application logic to handle the client verification,
which is done by the underlying transport-layer implementation.


## References
Prabath Siriwardena, Nuwan Dias. **Microservices Security in Action**. Manning, 2020

*Egon Teiniker, 2020-2021, GPL v3.0*
