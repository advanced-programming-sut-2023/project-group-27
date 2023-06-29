package model.chat;

import model.StrongholdCrusader;
import model.User;

import java.util.ArrayList;

public class Messenger {
    private static final ArrayList<Chat> allChats = new ArrayList<>();
    private static final PublicChat publicChat = new PublicChat();
    private static final ArrayList<PrivateChat> allPrivateChats = new ArrayList<>();
    private static final ArrayList<Room> allRooms = new ArrayList<>();

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
        allRooms.add(room);
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

    public static boolean roomExists(Room room) {
        return allRooms.contains(room);
    }

    public static void createChat(Chat chat) {
        allChats.add(chat);
    }
}
