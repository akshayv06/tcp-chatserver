package org.example.chat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class ChatServerApplication implements CommandLineRunner {

    @Value("${chat.server.port:4000}")
    private int port;

    private final ChatServer chatServer;

    public ChatServerApplication(ChatServer chatServer) {
        this.chatServer = chatServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(ChatServerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        chatServer.start(port);
    }
}
