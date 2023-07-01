package model.database;

import model.GameMap;
import model.User;
import model.chat.Message;
import model.chat.Messenger;
import model.chat.MessengerWrapper;

import java.util.ArrayList;

public class Data {
    private ArrayList<User> allUsers;
    private ArrayList<GameMap> allMaps;
    private MessengerWrapper wrapper;
    private ArrayList<Message> deletedMessages;

    public Data() {
        allUsers = new ArrayList<>();
        allMaps = new ArrayList<>();
        deletedMessages = new ArrayList<>();
    }

    public Data(ArrayList<User> users, ArrayList<GameMap> gameMaps ,
                MessengerWrapper wrapper , ArrayList<Message> deletedMessages) {
        allUsers = new ArrayList<>();
        allMaps = new ArrayList<>();
        this.deletedMessages = new ArrayList<>();
        this.wrapper = wrapper;
        addUsers(users);
        addGameMaps(gameMaps);
        addDeletedMessages(deletedMessages);

    }
    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<GameMap> getAllMaps() {
        return allMaps;
    }

    private void addUsers(ArrayList<User> users) {
        allUsers.addAll(users);
    }

    private void addGameMaps(ArrayList<GameMap> gameMaps) {
        allMaps.addAll(gameMaps);
    }

    private void addDeletedMessages(ArrayList<Message> deletedMessages) {
        this.deletedMessages.addAll(deletedMessages);
    }

    public MessengerWrapper getWrapper() {
        return wrapper;
    }

    public ArrayList<Message> getDeletedMessages() {
        return deletedMessages;
    }
}
