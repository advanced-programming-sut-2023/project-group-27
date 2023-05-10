package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StrongholdCrusader {
    private static User loggedInUser;
    private static User currentUser;
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

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        StrongholdCrusader.loggedInUser = loggedInUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        StrongholdCrusader.currentUser = currentUser;
    }

    public static void reset() {
        allUsers.clear();
        allMaps.clear();
    }

    public User getUserByUsername(String username) {
        return allUsers.getOrDefault(username, null);
    }

    public static HashMap<String, GameMap> getAllMaps() {
        return allMaps;
    }

    public static HashMap<String, User> getAllUsers() {
        return allUsers;
    }

    public static User[] getAllUsersList() {
        return allUsers.values().toArray(new User[0]);
    }

    public static GameMap[] getAllMapsList() {
        return allMaps.values().toArray(new GameMap[0]);
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