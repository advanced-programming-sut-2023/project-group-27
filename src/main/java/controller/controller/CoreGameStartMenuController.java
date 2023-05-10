package controller.controller;

import controller.view_controllers.GameStartMenuController;
import model.GameMap;
import model.StrongholdCrusader;
import model.User;
import view.GameStartMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class CoreGameStartMenuController {

    private final Scanner scanner;
    private final GameStartMenu gameStartMenu;
    private final GameStartMenuController gameStartMenuController;

    private final ArrayList<User> thisGamePlayers;

    private final User[] allUsers;
    private final GameMap[] allMaps;
    private GameMap selectedMap;

    public CoreGameStartMenuController(Scanner scanner, User loggedInUser) {
        this.scanner = scanner;
        this.gameStartMenuController = new GameStartMenuController(this);
        this.gameStartMenu = new GameStartMenu(gameStartMenuController, scanner);
        this.thisGamePlayers = new ArrayList<>();
        this.thisGamePlayers.add(loggedInUser);
        this.selectedMap = null;
        this.allUsers = StrongholdCrusader.getAllUsersList();
        this.allMaps = StrongholdCrusader.getAllMapsList();
        this.selectedMap = null;
    }

    public String run() {
        String gameStartMenuResult;
        while (true) {
            gameStartMenuResult = gameStartMenu.run();
        }
    }

    public String showAllUsers() {
        User user;
        String output = "all users:";
        for (int index = 0; index < allUsers.length; index++) {
            user = allUsers[index];
            output += "\n" + (index + 1) + "- name:" + user.getUsername() + " aka: " + user.getNickname() + "highscore: " + user.getHighScore();
        }
        return output;
    }

    public String showAllMaps() {
        GameMap map;
        String output = "all available maps:";
        for (int index = 0; index < allMaps.length; index++) {
            map = allMaps[index];
            output += "\n" + (index + 1) + "- name:" + map.getMapName()
                    + " capacity: " + map.getCapacity() + " heightXwidth: "
                    + map.getHeight() + "x" + map.getWidth();
        }
        return output;
    }

    public String showGamePlayers() {
        User user;
        String output = "selected players up to now:";
        for (int index = 0; index < thisGamePlayers.size(); index++) {
            user = thisGamePlayers.get(index);
            output += "\n" + (index + 1) + "- name:" + user.getUsername();
        }
        return output;
    }

    public String showSelectedMapName() {
        return ((selectedMap != null)
                ? ("name: " + selectedMap.getMapName() + " capacity: " + selectedMap.getCapacity())
                : "no map selected!");
    }

    public String addPlayer(int number) {
        if ((selectedMap != null && (selectedMap.getCapacity() == thisGamePlayers.size())) || (selectedMap == null && thisGamePlayers.size() == 8))
            return "capacity is already full!";
        if (number <= 0 || number > allUsers.length)
            return "number is invalid!";
        if (thisGamePlayers.contains(allUsers[number - 1]))
            return "this user is already selected!";
        thisGamePlayers.add(allUsers[number - 1]);
        return "player added successfully";
    }

    public String removePlayer(int number) {
        if (number <= 0 || number > thisGamePlayers.size())
            return "number is invalid!";
        if (number == 1)
            return "you can't remove yourself!";
        thisGamePlayers.remove(number - 1);
        return "removed player successfully!";
    }

    public String selectMap(int number) {
        if (number <= 0 || number > allUsers.length)
            return "number is invalid!";
        GameMap newMap = allMaps[number - 1];
        if (newMap.getCapacity() < thisGamePlayers.size())
            return "already selected players exceed this maps capacity, remove some and try again.";

    }
}