package model.chat;

import model.User;

import java.util.ArrayList;
import java.util.Date;

public class Message {
    private final User sender;
    private final Chat chat;
    private String content;
    private final String date;
    private boolean isSent;
    private boolean isSeen;
    private final ArrayList<Reactions> reactions;
    private final ArrayList<User> usersReacted;

    public Message(User sender, Chat chat , String content , String date) {
        this.sender = sender;
        this.chat = chat;
        this.content = content;
        this.date = date;
        isSent = false;
        isSeen = false;
        reactions = new ArrayList<>();
        usersReacted = new ArrayList<>();
    }

    public User getSender() {
        return sender;
    }

    public Chat getChat() {
        return chat;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public ArrayList<Reactions> getReactions() {
        return reactions;
    }

    public void addReaction(Reactions reaction) {
        reactions.add(reaction);
    }

    public void addUserReacted(User user) {
        usersReacted.add(user);
    }

    public void editMessage(String newContent) {
        content = newContent;
    }

    public void deleteMessage(boolean onlyForSender) {
        content = null;
    }
}
