package org.example.chat;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.*;

public class ClientHandler implements Runnable {

    private static final Map<String, PrintWriter> activeUsers = new ConcurrentHashMap<>();
    private static final int TIMEOUT_SECONDS = 60;

    private final Socket socket;
    private String username;
    private long lastActivityTime;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.lastActivityTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // Start idle timeout checker
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(() -> checkIdleTimeout(writer), 10, 10, TimeUnit.SECONDS);

            // Login Phase
            String loginLine = reader.readLine();
            if (loginLine == null || !loginLine.startsWith("LOGIN ")) {
                writer.println("ERR login-required");
                closeConnection(scheduler);
                return;
            }

            username = loginLine.substring(6).trim();
            if (username.isEmpty() || activeUsers.containsKey(username)) {
                writer.println("ERR username-taken");
                closeConnection(scheduler);
                return;
            }

            activeUsers.put(username, writer);
            writer.println("OK");
            broadcast("INFO " + username + " joined the chat", null);
            updateActivity();

            // Chat Phase(Broadcasting the message throughout the server)
            String message;
            while ((message = reader.readLine()) != null) {
                updateActivity();
                message = message.trim();
                if (message.isEmpty()) continue;

                if (message.equalsIgnoreCase("WHO")) {
                    for (String user : activeUsers.keySet()) {
                        writer.println("USER " + user);
                    }
                } else if (message.startsWith("MSG ")) {
                    String text = message.substring(4).trim();
                    broadcast("MSG " + username + " " + text, username);
                } else if (message.startsWith("DM ")) {
                    handleDirectMessage(message);
                } else if (message.equalsIgnoreCase("PING")) {
                    writer.println("PONG");
                } else {
                    writer.println("ERR unknown-command");
                }
            }

        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        } finally {
            cleanup();
        }
    }

    private void updateActivity() {
        this.lastActivityTime = System.currentTimeMillis();
    }

    private void checkIdleTimeout(PrintWriter writer) {
        long now = System.currentTimeMillis();
        if ((now - lastActivityTime) > TIMEOUT_SECONDS * 1000L) {
            writer.println("INFO disconnected due to inactivity");
            cleanup();
            closeSocket();
        }
    }

    private void handleDirectMessage(String message) {
        String[] parts = message.split(" ", 3);
        if (parts.length < 3) return;

        String target = parts[1];
        String text = parts[2];

        PrintWriter targetWriter = activeUsers.get(target);
        if (targetWriter != null) {
            targetWriter.println("DM " + username + " " + text);
        }
    }

    private void broadcast(String msg, String excludeUser) {
        for (Map.Entry<String, PrintWriter> entry : activeUsers.entrySet()) {
            if (!entry.getKey().equals(excludeUser)) {
                entry.getValue().println(msg);
            }
        }
    }

    private void cleanup() {
        if (username != null && activeUsers.containsKey(username)) {
            activeUsers.remove(username);
            broadcast("INFO " + username + " disconnected", null);
        }
        closeSocket();
    }

    private void closeSocket() {
        try {
            socket.close();
        } catch (IOException ignored) {}
    }

    private void closeConnection(ScheduledExecutorService scheduler) {
        scheduler.shutdownNow();
        closeSocket();
    }
}
