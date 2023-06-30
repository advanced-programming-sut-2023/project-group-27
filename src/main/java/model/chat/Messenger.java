package model.chat;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Messenger {
    private static final ArrayList<Chat> allChats = new ArrayList<>();
    private static final PublicChat publicChat = new PublicChat();
    private static final ArrayList<PrivateChat> allPrivateChats = new ArrayList<>();
    private static final HashMap<String , Room> allRooms = new HashMap<>();
    private static Chat currentChat;

    public static ArrayList<Chat> getAllChats() {
        return allChats;
    }

    public static PublicChat getPublicChat() {
        return publicChat;
    }

    public static void addPrivateChat(PrivateChat pv) {
        allPrivateChats.add(pv);
        allChats.add(pv);
    }

    public static void addRoom(Room room) {
        allRooms.put(room.getRoomName() , room);
        allChats.add(room);
    }

    public static PrivateChat getPrivateChatByUsers(User user1 , User user2) {
        for (PrivateChat privateChat : allPrivateChats) {
            if (privateChat.getParticipantUserByName(user1.getUsername()) != null
            && privateChat.getParticipantUserByName(user2.getUsername()) != null)
                return privateChat;
        }
        return null;
    }

    public static ArrayList<PrivateChat> getUsersPrivateChats(User user) {
        ArrayList<PrivateChat> arrayList = new ArrayList<>();
        for (PrivateChat privateChat : allPrivateChats) {
            if (privateChat.getParticipantUserByName(user.getUsername()) != null)
                arrayList.add(privateChat);
        }
        return arrayList;
    }

    public static ArrayList<Room> getUsersRooms(User user) {
        ArrayList<Room> arrayList = new ArrayList<>();
        for (Room room : allRooms.values()) {
            if (room.getParticipantUserByName(user.getUsername()) != null)
                arrayList.add(room);
        }
        return arrayList;
    }

    public static Chat getCurrentChat() {
        return currentChat;
    }

    public static void setCurrentChat(Chat currentChat) {
        Messenger.currentChat = currentChat;
    }

    public static Room roomExists(String roomName) {
        return allRooms.get(roomName);
    }

}
