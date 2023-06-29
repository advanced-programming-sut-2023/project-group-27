package model.chat;

import model.User;

import java.util.ArrayList;

public class Messenger {
    private static final ArrayList<Chat> allChats = new ArrayList<>();
    private static final PublicChat publicChat = new PublicChat();
    private static final ArrayList<PrivateChat> allPrivateChats = new ArrayList<>();
    private static final ArrayList<Room> allRooms = new ArrayList<>();


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

    public static boolean privateChatExists(User user1 , User user2) {
        for (PrivateChat privateChat : allPrivateChats) {
            if (privateChat.getParticipantUsers().contains(user1)
            && privateChat.getParticipantUsers().contains(user2))
                return true;
        }
        return false;
    }

    public static boolean roomExists(Room room) {
        return allRooms.contains(room);
    }

    public static void createChat(Chat chat) {
        allChats.add(chat);
    }
}
