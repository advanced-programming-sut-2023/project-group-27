package model.chat;

import java.util.ArrayList;

public class Messenger {
    private static final ArrayList<Chat> allChats = new ArrayList<>();

    public static void createChat(Chat chat) {
        allChats.add(chat);
    }
}
