package model.chat;

import model.User;

import java.util.ArrayList;

public class Chat {
    private final ArrayList<User> participantUsers;
    private final ArrayList<Message> allMessages;

    public Chat() {
        this.participantUsers = new ArrayList<>();
        this.allMessages = new ArrayList<>();
    }

    public ArrayList<User> getParticipantUsers() {
        return participantUsers;
    }

    public ArrayList<Message> getAllMessages() {
        return allMessages;
    }

    public void addUSerToChat(User user) {
        participantUsers.add(user);
    }

    public void addNewMessage(Message message) {
        allMessages.add(message);
    }

    public void sendMessage(Message message) {
        allMessages.add(message);
    }
}
