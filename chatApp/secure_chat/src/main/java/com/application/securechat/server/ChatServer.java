package com.application.securechat.server;

import com.application.securechat.group.GroupManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ChatServer {
    private static final int PORT = 1234;
    private static final ExecutorService pool = Executors.newFixedThreadPool(4);

    private static GroupManager groupManager = new GroupManager();
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat Server is listening on port " + PORT);


            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Connected to client");
                    ClientHandler clientThread = new ClientHandler(clientSocket, groupManager);
                    pool.execute(clientThread);
                } catch (IOException e) {
                    System.out.println("Error accepting client connection: " + e.getMessage());
                } catch (NoSuchAlgorithmException e) {
                    System.out.println("Algorithm is not valid" + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error starting Server on port " + PORT + ": " + e.getMessage());
        }
    }
}