package model.chat;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.HashMap;

public class MessengerWrapper {
    private final PublicChat publicChat;
    private final ArrayList<PrivateChat> allPrivateChats = new ArrayList<>();
    private final HashMap<String , Room> allRooms = new HashMap<>();

    public MessengerWrapper() {
        publicChat = Messenger.getPublicChat();
        allPrivateChats.addAll(Messenger.getAllPrivateChats());
        allRooms.putAll(Messenger.getAllRooms());
    }


    public PublicChat getPublicChat() {
        return publicChat;
    }

    public HashMap<String , Room> getAllRooms() {
        return allRooms;
    }

    public ArrayList<PrivateChat> getAllPrivateChats() {
        return allPrivateChats;
    }
}
