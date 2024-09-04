package com.application.securechat.message;
public class TextMessage implements Message {
    private String content;
    public TextMessage(String content) {
        this.content = content;
    }
    @Override
    public String deliverMessage(String groupId) {

        return ("Message from group "+groupId+": "+content);
    }
}
