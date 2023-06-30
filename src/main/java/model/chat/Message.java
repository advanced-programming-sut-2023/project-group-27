package model.chat;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.User;

import java.util.HashMap;

public class Message {
    private final User sender;
    private final Chat chat;
    private String content;
    private final String date;
    private boolean isSent;
    private boolean isSeen;
    private final HashMap<User , Reactions> reactions;
    private HBox showMessage;
    private static final HashMap<Message , Text> messageToText = new HashMap<>();

    public Message(User sender, Chat chat, String content, String date) {
        this.sender = sender;
        this.chat = chat;
        this.content = content;
        this.date = date;
        isSent = false;
        isSeen = false;
        reactions = new HashMap<>();
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
        //TODO implement with server
        isSent = sent;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        //TODO implement with server
        isSeen = seen;
    }

    public HashMap<User, Reactions> getReactions() {
        return reactions;
    }

    public void addReaction(User user , Reactions reaction) {
        reactions.put(user , reaction);
    }

    public Reactions usersReaction(User user) {
        return reactions.get(user);
    }


    public static Text getTextByMessage(Message message) {
        return messageToText.get(message);
    }

    public static void addTextToMessage(Message message , Text text) {
        messageToText.put(message , text);
    }

    public HBox getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(HBox showMessage) {
        this.showMessage = showMessage;
    }

    public void editMessage(String newContent) {
        content = newContent;
    }

    public void deleteMessage(boolean onlyForSender) {
        Messenger.getCurrentChat().removeMessage(this);
        //TODO implement message deletion for others
    }
}
