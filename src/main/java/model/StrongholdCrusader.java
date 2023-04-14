package model;

import java.util.HashMap;
import java.util.Map;

public class StrongholdCrusader {
    private static User CurrentUser;
    private static HashMap<String , Map> allMaps;
    private static HashMap<String , User> allUsers;

    public static User getCurrentUser() {
        return CurrentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }

    public static HashMap<String, Map> getAllMaps() {
        return allMaps;
    }

    public static HashMap<String, User> getAllUsers() {
        return allUsers;
    }

    public static void addUser(User user)
    {

    }

    public static void addMap(Map map)
    {

    }
}
