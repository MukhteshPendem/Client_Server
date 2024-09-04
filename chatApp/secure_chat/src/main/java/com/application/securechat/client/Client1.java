package com.application.securechat.client;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Client1 {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        ChatClient c1 = new ChatClient("localhost",1234);
        c1.start();
    }


}
