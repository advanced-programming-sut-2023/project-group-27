package model.chat;

import model.User;

import java.util.ArrayList;

public class Chat {
    private ArrayList<User> participantUsers;
    private ArrayList<Message> allMessages;

    public void sendMessage(Message message) {
        allMessages.add(message);
    }

    public void addUSerToChat(User user) {
        participantUsers.add(user);
    }
}
