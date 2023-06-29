package model.chat;

import model.User;

public class PrivateChat extends Chat{
    private final User user1;
    private final User user2;

    public PrivateChat(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }
}
