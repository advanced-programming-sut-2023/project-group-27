package model.chat;

import model.User;

import java.util.Date;

public class Message {
    private final User sender;
    private final Chat chat;
    private String content;
    private final Date date;
    private boolean isSeen;

    public Message(User sender, Chat chat , String content , Date date) {
        this.sender = sender;
        this.chat = chat;
        this.content = content;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public void editMessage(String newContent) {
        content = newContent;
    }

    public void deleteMessage() {
        content = null;
    }
}
