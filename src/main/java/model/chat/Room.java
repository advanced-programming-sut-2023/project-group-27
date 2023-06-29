package model.chat;

import model.User;

public class Room extends Chat{
    private final String roomName;

    public Room(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void addUserToRoom(User user) {
        super.addUserToChat(user);
    }
}
