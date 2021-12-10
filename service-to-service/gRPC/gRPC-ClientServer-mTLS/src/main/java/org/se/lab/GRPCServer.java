package org.se.lab;

import io.grpc.Server;
import io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.NettyServerBuilder;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class GRPCServer
{
    private static final Logger LOG = Logger.getLogger(GRPCServer.class.getName());

    private Server server;

    private final String host;
    private final int port;
    private final String certChainFilePath;
    private final String privateKeyFilePath;
    private final String trustCertCollectionFilePath;


    public GRPCServer(String host, int port,
                      String certChainFilePath, String privateKeyFilePath, String trustCertCollectionFilePath)
    {
        this.host = host;
        this.port = port;
        this.certChainFilePath = certChainFilePath;
        this.privateKeyFilePath = privateKeyFilePath;
        this.trustCertCollectionFilePath = trustCertCollectionFilePath;
    }

    public static void main(String[] args) throws IOException, InterruptedException
    {
        final GRPCServer server = new GRPCServer("localhost",50440,
                "/tmp/sslcert/server.crt", "/tmp/sslcert/server.pem", "/tmp/sslcert/ca.crt");
        server.start();
        server.blockUntilShutdown();
    }

    private SslContextBuilder getSslContextBuilder()
    {
        SslContextBuilder sslClientContextBuilder = SslContextBuilder.forServer(new File(certChainFilePath),
                new File(privateKeyFilePath));
        if (trustCertCollectionFilePath != null)
        {
            sslClientContextBuilder.trustManager(new File(trustCertCollectionFilePath));
            sslClientContextBuilder.clientAuth(ClientAuth.REQUIRE);
        }
        return GrpcSslContexts.configure(sslClientContextBuilder, SslProvider.OPENSSL);
    }

    private void start() throws IOException
    {
        server = NettyServerBuilder.forAddress(new InetSocketAddress(host, port))
                .addService(new HelloServiceImpl())
                .sslContext(getSslContextBuilder().build())
                .build()
                .start();
        LOG.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                GRPCServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop()
    {
        if (server != null)
        {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException
    {
        if (server != null)
        {
            server.awaitTermination();
        }
    }
}
