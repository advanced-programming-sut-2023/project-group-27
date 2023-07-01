package model.chat;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Chat {
    private final HashMap<String , User> participantUsers;
    private final ArrayList<Message> allMessages;

    public Chat() {
        this.participantUsers = new HashMap<>();
        this.allMessages = new ArrayList<>();
    }

    public HashMap<String , User> getParticipantUsers() {
        return participantUsers;
    }

    public User getParticipantUserByName(String username) {
        return participantUsers.get(username);
    }

    public ArrayList<Message> getAllMessages() {
        return allMessages;
    }

    public void addUserToChat(User user) {
        participantUsers.put(user.getUsername() , user);
    }

    public void addNewMessage(Message message) {
        allMessages.add(message);
    }

    public void removeMessage(Message message) {
        allMessages.remove(message);
    }

}
