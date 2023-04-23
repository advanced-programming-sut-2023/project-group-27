package model;

import java.util.ArrayList;
import java.util.HashMap;

public class StrongholdCrusader {
    private static User CurrentUser;
    private static GameMap currentMatchMap;
    private int turnCounter;
    private static final HashMap<String , GameMap> allMaps = new HashMap<>();

    private static final HashMap<String , User> allUsers = new HashMap<>();

    public static User getCurrentUser() {
        return CurrentUser;
    }

    public static GameMap getCurrentMatchMap() {
        return currentMatchMap;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }

    public static GameMap getCurrentMap() {
        return currentMatchMap;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public User getUserByUsername(String username) {
        return allUsers.getOrDefault(username, null);
    }

    public void addTurnCounter(){
        turnCounter++;
    }

    public static HashMap<String, GameMap> getAllMaps() {
        return allMaps;
    }

    public static HashMap<String, User> getAllUsers() {
        return allUsers;
    }

    public static void addUser(User user) {
        allUsers.put(user.getUsername(), user);
    }

    public static void addMap(GameMap map) {
        allMaps.put(map.getMapName(), map);
    }

    public static void removeUser(User user) {
        allUsers.remove(user.getUsername());
    }

    public static void removeMap(GameMap map) {
        allMaps.remove(map.getMapName());
    }

    public static void addAllUsers(ArrayList<User> usersToBeAdded) {
        for (User currentUser : usersToBeAdded)
            addUser(currentUser);
    }

    public static void addAllMaps(ArrayList<GameMap> mapsToBeAdded) {
        for (GameMap currentMap : mapsToBeAdded)
            addMap(currentMap);
    }
}
