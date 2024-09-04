package com.application.securechat.group;


import com.application.securechat.message.Message;
import com.application.securechat.server.ClientHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
public class Group {
    private final Set<ClientHandler> members = Collections.synchronizedSet(new HashSet<>());
    public Group(String id) {
    }
    public synchronized void addMember(ClientHandler member) {
        if(!members.contains(member)) {
            members.add(member);
        }
        else{
            System.out.println("already in the group");
        }
    }
    public void sendMessage(String groupID,Message message) {
        for (ClientHandler member : members) {
            member.sendMessage(groupID,message);
        }
    }
}