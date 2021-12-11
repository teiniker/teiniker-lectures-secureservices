package org.se.lab.server;

import io.grpc.Server;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyServerBuilder;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

import static io.grpc.netty.GrpcSslContexts.configure;

public class GRPCServer
{
    private static final Logger LOG = Logger.getLogger(GRPCServer.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException
    {
        SslContextBuilder sslClientContextBuilder = SslContextBuilder.forServer(new File("/tmp/sslcert/server.crt"),
                new File("/tmp/sslcert/server.pem"))
                .trustManager(new File("/tmp/sslcert/ca.crt"))
                .clientAuth(ClientAuth.REQUIRE);

        SslContext sslContext = GrpcSslContexts.configure(sslClientContextBuilder, SslProvider.OPENSSL).build();

        Server server = NettyServerBuilder.forAddress(new InetSocketAddress("localhost",50440))
                .addService(new HelloServiceImpl())
                .sslContext(sslContext)
                .build()
                .start();
        LOG.info("Server started...");

        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                server.shutdown();
                System.err.println("*** server shut down");
            }
        });

        server.awaitTermination();
    }
}
