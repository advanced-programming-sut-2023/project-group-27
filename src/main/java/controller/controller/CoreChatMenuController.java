package controller.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.StrongholdCrusader;
import model.User;
import model.chat.*;
import server.Connection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CoreChatMenuController {
    private User loggedInUser;
    private Connection connection;
    public Message sendMessage(String messageContent) {
        loggedInUser = StrongholdCrusader.getLoggedInUser();
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HH:mm");
        Message message = new Message(
                loggedInUser , Messenger.getCurrentChat(),messageContent , format.format(date));
        Messenger.getCurrentChat().addNewMessage(message);
        updateChat();
        return message;
    }

    public String startNewPrivateChat(String username) {
        loggedInUser = StrongholdCrusader.getLoggedInUser();
        User user = StrongholdCrusader.getUserByName(username);
        if (user == null) return null;
        if (Messenger.getPrivateChatByUsers(loggedInUser , user) != null)
            return "Private chat already exists";
        PrivateChat pv = new PrivateChat(loggedInUser, user);
        Messenger.addPrivateChat(pv);
        updateChat();
        return "New Private chat started with " + username + "!";
    }

    public Room createNewRoom(String roomsName) {
        loggedInUser = StrongholdCrusader.getLoggedInUser();
        Room room = Messenger.roomExists(roomsName);
        if (room != null)
            return null;
        room = new Room(roomsName);
        room.addUserToChat(loggedInUser);
        Messenger.addRoom(room);
        updateChat();
        return room;
    }

    public void updateChat() {
        connection = Utilities.getChatRoomConnection();
        connection.request("update");
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.excludeFieldsWithModifiers(
                java.lang.reflect.Modifier.TRANSIENT).create();
        MessengerWrapper wrapper = Messenger.getWrapper();
        String request = gson.toJson(wrapper);
        connection.request(request);
    }
}
