package model.database;

import model.GameMap;
import model.User;

import java.util.ArrayList;

public class Data {
    private ArrayList<User> allUsers;
    private ArrayList<GameMap> allMaps;

    public Data() {
        allUsers = new ArrayList<>();
        allMaps = new ArrayList<>();
    }

    public Data(ArrayList<User> users, ArrayList<GameMap> gameMaps) {
        allUsers = new ArrayList<>();
        allMaps = new ArrayList<>();

        addUsers(users);
        addGameMaps(gameMaps);
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
}
