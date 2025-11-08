package org.example.chat;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ChatServer {

    private final ExecutorService clientPool = Executors.newCachedThreadPool();

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[+] Chat Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getRemoteSocketAddress());
                clientPool.submit(new ClientHandler(clientSocket));
            }

        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
