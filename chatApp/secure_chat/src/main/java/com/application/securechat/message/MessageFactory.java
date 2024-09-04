package com.application.securechat.message;

public class MessageFactory {
    public static Message createMessage(String type, String content) {
        if ("text".equalsIgnoreCase(type)) {
            return new TextMessage(content);


        }

        return null;
    }
}