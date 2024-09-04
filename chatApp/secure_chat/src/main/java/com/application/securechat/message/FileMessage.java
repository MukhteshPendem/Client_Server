package com.application.securechat.message;

public class FileMessage implements Message {
    private String path;
    public FileMessage(String path) {
        this.path = path;
    }
    @Override
    public String deliverMessage(String groupId) {



        return ("file  from group "+groupId+": "+path);
    }
}
