package controller.controller;

import javafx.scene.input.DataFormat;
import model.StrongholdCrusader;
import model.User;
import model.chat.Message;
import model.chat.Messenger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class CoreChatMenuController {
    private User loggedInUser = StrongholdCrusader.getLoggedInUser();
    public Message sendMessagePublicChat(String messageContent) {
        User loggedInUser = StrongholdCrusader.getLoggedInUser();
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HHmm");
        //TODO access chat
        Message message = new Message(
                loggedInUser , Messenger.getPublicChat(),messageContent , format.format(date));
        Messenger.getPublicChat().addNewMessage(message);
        return message;
    }
}
