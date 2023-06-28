package model.chat;

import java.util.ArrayList;

public class Messenger {
    private static final PublicChat publicChat = new PublicChat();
    private static final ArrayList<Chat> allChats = new ArrayList<>();

    public static void createChat(Chat chat) {
        allChats.add(chat);
    }

    public static PublicChat getPublicChat() {
        return publicChat;
    }
}
