package model.chat;

import model.StrongholdCrusader;
import model.User;

public class PrivateChat extends Chat{
    private final User user1;
    private final User user2;

    public PrivateChat(User user1, User user2) {
        super.addUserToChat(user1);
        super.addUserToChat(user2);
        this.user1 = user1;
        this.user2 = user2;
    }

    public User getOtherUser() {
        if (user1.getUsername().equals(StrongholdCrusader.
                getLoggedInUser().getUsername()))
            return user2;
        else if (user2.getUsername().equals(StrongholdCrusader.
                getLoggedInUser().getUsername()))
            return user1;
        return null;
    }
}
