package com.application.securechat.client;


import com.application.securechat.cryptutils.Utilities;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.Socket;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
public class ChatClient {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    public final KeyPair keyPair ;
    public final SecretKey secretKey;
    private DataInputStream din;
    private DataOutputStream dout;
    Utilities utilities;
    public ChatClient(String serverAddress, int serverPort) throws IOException, NoSuchAlgorithmException {
        socket = new Socket(serverAddress, serverPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        din = new DataInputStream(socket.getInputStream());
        dout = new DataOutputStream(socket.getOutputStream());
         utilities = new Utilities();
        this.secretKey = utilities.generateAESKey();
        this.keyPair = utilities.keyPairGenerator();

    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("[CLIENT] Connected to the server. Type 'quit' to exit.");
        System.out.println("Usage: msg:<groupId>:messageType:content");


        new Thread(() -> {
            try {
                String serverMessage;
                while ((serverMessage = in.readLine()) != null) {
                    System.out.println(serverMessage);
                }
            } catch (IOException e) {
                System.out.println("Connection to server lost: " + e.getMessage());
            }
        }).start();

        try {
            dout.writeUTF(utilities.secretKeyToString(secretKey));
            dout.flush();
            while (true) {

                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("quit")) {
                    out.println(userInput);
                    break;
                }

                out.println(utilities.encryptAES(userInput,secretKey));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
                scanner.close();
            } catch (IOException e) {
                System.out.println("Error when closing socket or scanner: " + e.getMessage());
            }
        }
    }

}
