package com.manning.mss.ch08.sample01;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * The Inventory Server process.
 */
public class InventoryServer {

    private static final Logger logger = Logger.getLogger(InventoryServer.class.getName());

    private Server server;

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        final InventoryServer server = new InventoryServer();
        server.start();
        server.blockUntilShutdown();
    }

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new InventoryImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                InventoryServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {

        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {

        if (server != null) {
            server.awaitTermination();
        }
    }

    static class InventoryImpl extends InventoryGrpc.InventoryImplBase {

        /**
         * Method that updates the inventory upon receiving a message.
         * @param req - The request
         * @param responseObserver - A handle to the response
         */
        @Override
        public void updateInventory(Order req, StreamObserver<UpdateReply> responseObserver) {

            UpdateReply updateReply = UpdateReply.newBuilder().setMessage("Updated inventory for " + req.getItemsCount()
                    + " products").build();
            responseObserver.onNext(updateReply);
            responseObserver.onCompleted();
        }
    }
}
