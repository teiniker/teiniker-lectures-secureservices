# Securing Communication Between Microservices and the API Gateway 

## Securing the Communication
We have to consider what happens if someone accesses the microservice directly,
**bypassing the API gateway** layer.

There are three common ways to secure communications among services in a microservices deployment:
* **Trust the Network**: No security is enforced in service-to-service communication. 
  This model relies on network-level security which must guarantee that no attacker can intercept 
  communications among services.  

* **Mutual TLS**: Each microservice in the deployment has to carry a public/private key pair and uses 
  that key pair to authenticate to the receiver microservice via mTLS. 
  Challenges in mTLS include bootstrapping trust and key/certificates management.\
  _Example_: [Using OpenSSL](mTLS/OpenSSL)\
  _Exercise_: [Mutual Transport Layer Security](mTLS/SpringBoot-ArticleService-mTLS-Exercise) ([Model Solution](mTLS/SpringBoot-ArticleService-mTLS))

* **JSON Web Tokens**: JWT works at the application layer and is a container that carry a set of claims 
  (end-user attributes) and is signed by the issuer.


## Microservices using gRPC 

gRPC is a high performance, open source RPC framework which is based on a client-server model of **remote procedure calls**. A client application can directly call methods on a server application as if it was a local object.
* [Introduction](gRPC/introduction)
* Protocol Buffers
  * _Example_: [Protocol Buffers](gRPC/gRPC-ProtocolBuffers)
* gRPC Services
  * _Example_: [gRPC-ClientServer](gRPC/gRPC-ClientServer)
  * _Exercise_: [gRPC-ArticleService](gRPC/gRPC-ArticleService-Exercise) ([Model Solution](gRPC/gRPC-ArticleService))


## References
Prabath Siriwardena, Nuwan Dias. **Microservices Security in Action**. Manning, 2020

*Egon Teiniker, 2016-2022, GPL v3.0*
