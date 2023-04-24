package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StrongholdCrusader {
    private static User CurrentUser;
    private static GameMap currentMatchMap;
    private int turnCounter;
    private static final HashMap<String , GameMap> allMaps = new HashMap<>();
    private static final HashMap<String , User> allUsers = new HashMap<>();
    private static final List<String> securityQuestions = List.of(
            "What is my father’s name?",
            "What was my first pet’s name?",
            "What is my mother’s last name?",
            "What is your favorite video game?",
            "What was name of your first crush?"
    );

    public static User getUserByName(String name) {
        return allUsers.get(name);
    }

    public static List<String> getSecurityQuestions() {
        return securityQuestions;
    }

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
