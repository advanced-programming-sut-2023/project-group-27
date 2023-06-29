package controller.controller;

import model.StrongholdCrusader;
import model.User;
import model.chat.Message;
import model.chat.Messenger;
import model.chat.PrivateChat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CoreChatMenuController {
    private User loggedInUser;
    public Message sendMessagePublicChat(String messageContent) {
        loggedInUser = StrongholdCrusader.getLoggedInUser();
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HH:mm");
        Message message = new Message(
                loggedInUser , Messenger.getPublicChat(),messageContent , format.format(date));
        Messenger.getPublicChat().addNewMessage(message);
        return message;
        //TODO access chat
    }

    public String startNewPrivateChat(String username) {
        loggedInUser = StrongholdCrusader.getLoggedInUser();
        User user = StrongholdCrusader.getUserByName(username);
        if (user == null) return null;
        if (Messenger.privateChatExists(loggedInUser , user))
            return "Private chat already exists";
        PrivateChat pv = new PrivateChat(loggedInUser, user);
        Messenger.addPrivateChat(pv);
        return "New Private chat started with " + username + "!";
    }
}
