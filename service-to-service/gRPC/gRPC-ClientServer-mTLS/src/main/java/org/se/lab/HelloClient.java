package org.se.lab;

import io.grpc.ManagedChannel;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

import javax.net.ssl.SSLException;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class HelloClient
{
    private static final Logger LOG = Logger.getLogger(HelloClient.class.getName());

    private final ManagedChannel channel;
    private final  HelloServiceGrpc.HelloServiceBlockingStub stub;

    private static SslContext buildSslContext(String trustCertCollectionFilePath, String clientCertChainFilePath,
                                              String clientPrivateKeyFilePath) throws SSLException
    {
        SslContextBuilder builder = GrpcSslContexts.forClient();
        if (trustCertCollectionFilePath != null)
        {
            builder.trustManager(new File(trustCertCollectionFilePath));
        }
        if (clientCertChainFilePath != null && clientPrivateKeyFilePath != null)
        {
            builder.keyManager(new File(clientCertChainFilePath), new File(clientPrivateKeyFilePath));
        }
        return builder.build();
    }

    /**
     * Construct client connecting to the server at {@code host:port}.
     */
    public HelloClient(String host, int port, SslContext sslContext) throws SSLException
    {
        this(NettyChannelBuilder.forAddress(host, port)
                .negotiationType(NegotiationType.TLS)
                .sslContext(sslContext)
                .build());
    }

    /**
     * Construct client for accessing RouteGuide server using the existing channel.
     */
    HelloClient(ManagedChannel channel)
    {
        this.channel = channel;
        stub = HelloServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException
    {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public String sayHello()
    {
        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Homer")
                .setLastName("Simpson")
                .build());

        return helloResponse.getGreeting();
    }

    public static void main(String[] args) throws Exception
    {
        HelloClient client= new HelloClient("localhost", 50440,
                buildSslContext("/tmp/sslcert/ca.crt", "/tmp/sslcert/client.crt", "/tmp/sslcert/client.pem"));

        String greetings = client.sayHello();
        System.out.println("Response received from server:\n" + greetings);
        client.shutdown();
    }
}
