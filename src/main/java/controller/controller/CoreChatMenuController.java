package controller.controller;

import com.google.gson.Gson;
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
        connection = Utilities.getChatRoomConnection();
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
        connection = Utilities.getChatRoomConnection();
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
        connection = Utilities.getChatRoomConnection();
        updateChat();
        return room;
    }

    private void updateChat() {
        connection.request("update");
        Gson gson = new Gson();
        String request = gson.toJson(Messenger.getWrapper());
        connection.request(request);
    }
}
