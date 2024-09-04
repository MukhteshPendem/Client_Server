package com.application.securechat.group;

import com.application.securechat.message.Message;
import com.application.securechat.server.ClientHandler;
import java.util.HashMap;
import java.util.Map;
public class GroupManager {
    private Map<String, Group> groups = new HashMap<>();
    public synchronized void addGroup(String groupId) {
        groups.putIfAbsent(groupId, new Group(groupId));
    }
    public synchronized void addMember(String groupId, ClientHandler member) {
        Group group = groups.get(groupId);
        if (group == null) {
            group = new Group(groupId);
            groups.put(groupId, group);
        }
        group.addMember(member);
    }
    public synchronized void sendMessageToGroup(String groupId, Message message) {
        if (groups.containsKey(groupId)) {
            groups.get(groupId).sendMessage(groupId,message);
        }
    }
}