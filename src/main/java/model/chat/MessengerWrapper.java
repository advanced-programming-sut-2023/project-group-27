package model.chat;

import java.util.ArrayList;
import java.util.HashMap;

public class MessengerWrapper {
    private final ArrayList<Chat> allChats = new ArrayList<>();
    private final PublicChat publicChat;
    private final ArrayList<PrivateChat> allPrivateChats = new ArrayList<>();
    private final HashMap<String , Room> allRooms = new HashMap<>();
    private Chat currentChat;

    public MessengerWrapper() {
        allChats.addAll(Messenger.getAllChats());
        publicChat = Messenger.getPublicChat();
        allPrivateChats.addAll(Messenger.getAllPrivateChats());
        allRooms.putAll(Messenger.getAllRooms());
    }

    public ArrayList<Chat> getAllChats() {
        return allChats;
    }

    public PublicChat getPublicChat() {
        return publicChat;
    }

    public Chat getCurrentChat() {
        return currentChat;
    }

    public HashMap<String , Room> getAllRooms() {
        return allRooms;
    }

    public ArrayList<PrivateChat> getAllPrivateChats() {
        return allPrivateChats;
    }
}
