package model;

import java.util.HashMap;

public class StrongholdCrusader {
    private static User CurrentUser;
    private static GameMap currentMatchMap;
    private int turnCounter;
    private static HashMap<String , GameMap> allMaps = new HashMap<>();

    private static HashMap<String , User> allUsers = new HashMap<>();

    public static User getCurrentUser() {
        return CurrentUser;
    }

    public static GameMap getCurrentMatchMap() {
        return currentMatchMap;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }

    public int getTurnCounter() {
        return turnCounter;
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

    public static void addUser(User user)
    {

    }

    public static void addMap(GameMap map)
    {

    }
}
