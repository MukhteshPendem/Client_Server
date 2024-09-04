package com.application.securechat.client;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Client2 {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        ChatClient c2 = new ChatClient("localhost",1234);
        c2.start();
    }
}
