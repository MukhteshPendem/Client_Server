package com.application.securechat.server;

import com.application.securechat.group.GroupManager;
import com.application.securechat.message.Message;
import com.application.securechat.message.MessageFactory;
import com.application.securechat.cryptutils.Utilities;

import javax.crypto.SecretKey;
import java.io.*;
import java.net.Socket;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private GroupManager groupManager;
    private KeyPair keyPair;
    private DataInputStream din;
    private DataOutputStream dout;

    Utilities utilities;



    public ClientHandler(Socket socket, GroupManager groupManager) throws IOException, NoSuchAlgorithmException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.groupManager = groupManager;
         utilities = new Utilities();
        keyPair = utilities.keyPairGenerator();
        din = new DataInputStream(socket.getInputStream());
        dout = new DataOutputStream(socket.getOutputStream());

    }
    @Override
    public void run() {
        try {
            System.out.println("hi");
            SecretKey s = utilities.stringToSecretKey(din.readUTF(),"AES");

            System.out.println(s);
            String input;
            while ((input = utilities.decryptAES(in.readLine(),s)) != null) {
                String[] parts = input.split(":");
                if (parts[0].equals("msg") && parts.length == 4) {
                    String groupId = parts[1];
                    String type = parts[2];
                    String content = parts[3];

                    Message message = MessageFactory.createMessage(type, content);
                    groupManager.sendMessageToGroup(groupId, message);
                } else if (parts[0].equals("join") && parts.length == 2) {
                    String groupId = parts[1];
                    groupManager.addGroup(groupId);
                    groupManager.addMember(groupId, this);
                } else if (parts[0].equalsIgnoreCase("quit") && parts.length ==1) {
                    System.out.println("connection closed");
                    break;
                    
                }

                else {
                    System.out.println("Please follow the usage rules Strictly");
                }
            }
        } catch (IOException e) {
            System.err.println("Error in ClientHandler: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                System.err.println("Could not close streams or socket: " + e.getMessage());
            }
        }
    }
    public void sendMessage(String groupId,Message message) {

       out.println(message.deliverMessage(groupId));
    }
}