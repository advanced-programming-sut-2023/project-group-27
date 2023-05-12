package controller.controller;

import controller.view_controllers.GameStartMenuController;
import model.*;
import model.castle_components.CastleComponent;
import model.castle_components.CastleComponentType;
import model.man.Man;
import model.man.Soldier;
import model.man.SoldierType;
import view.GameStartMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CoreGameStartMenuController {

    private final Scanner scanner;
    private final GameStartMenu gameStartMenu;
    private final MonarchyColorType[] colorTypes;
    private final GameStartMenuController gameStartMenuController;

    private final ArrayList<User> thisGamePlayers;
    private final HashMap<User, MonarchyColorType> colors;
    private final HashMap<User, Cell> keepCells;
    private final User[] allUsers;
    private final GameMap[] allMaps;
    private GameMap selectedMap;
    private Match match;

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
        this.colors = new HashMap<>();
        this.colorTypes = MonarchyColorType.values();
        keepCells = new HashMap<>();
    }

    public void run() {
        String gameStartMenuResult;
        while (true) {
            gameStartMenuResult = gameStartMenu.run();
            switch (gameStartMenuResult) {
                case "Exit":
                    return;
                case "Start Game":
                    (new CoreGameMenuController(match, scanner)).run();
                    return;
                case "Enter Map Navigation Menu":
                    (new CoreMapNavigationMenuController(40, 40, scanner, selectedMap)).run();
                    break;
                default:
                    break;
            }
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
            output += "\n" + (index + 1) + "- name:" + user.getUsername() + " color: "
                    + (colors.containsKey(user) ? colors.get(user).getColorName() : "-") + " keepLocationNumber: "
                    + (keepCells.containsKey(user) ? keepCells.get(user) : "-");
        }
        return output;
    }

    public String showSelectedMapInfo() {
        if (selectedMap != null) {
            String output = "name: " + selectedMap.getMapName() + " capacity: " + selectedMap.getCapacity() + "\nkeep positions:";
            for (Cell cell : selectedMap.getKeepsLocations())
                output += "\n" + cell.getLocation().toString();
            return output;
        }
        return "no map selected!";
    }

    public String addPlayer(int number) {
        if ((selectedMap != null &&
                (selectedMap.getCapacity() == thisGamePlayers.size()))
                || (selectedMap == null && thisGamePlayers.size() == 8))
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
        if (colors.containsKey(thisGamePlayers.get(number - 1)))
            colors.remove(thisGamePlayers.get(number - 1));

        thisGamePlayers.remove(number - 1);
        return "removed player successfully!";
    }

    public String selectMap(int number) {
        if (number <= 0 || number > allUsers.length)
            return "number is invalid!";

        GameMap newMap = allMaps[number - 1];

        if (newMap.getCapacity() < thisGamePlayers.size())
            return "already selected players exceed this maps capacity, remove some and try again.";
        selectedMap = newMap;
        return "successfully changed map";
    }

    public String navigateSelectedMap() {
        return  (selectedMap == null) ? "no map selected!" : "Enter Map Navigation Menu";
    }

    public String showAllColors() {
        String output = "colors:";
        for (int index = 0; index < colorTypes.length; index++) {
            MonarchyColorType type = colorTypes[index];
            output += "\n" + (index + 1) + "- name: " + type.getColorName();
        }
        return output;
    }

    public String setColor(int number, MonarchyColorType colorType) {
        if (number <= 0 || number > thisGamePlayers.size())
            return "player number is invalid!";
        if (colors.containsValue(colorType))
            return "This color is already used";

        colors.put(thisGamePlayers.get(number - 1), colorType);
        return "Color set successfully.";
    }

    public void resetColors() {
        colors.clear();
    }

    public String assignKeepsAndStartGame(int[] numbers) {
        if (selectedMap == null)
            return "select a map first!";
        if (numbers.length != thisGamePlayers.size())
            return "invalid numbers. can't match to players.";

        Cell[] mapsKeepCells = selectedMap.getKeepsLocations();
        ArrayList<Integer> cellsToAssign = new ArrayList<>();

        for (int i = 0; i < thisGamePlayers.size(); i++) {
            if (numbers[i] <= 0 || numbers[i] > mapsKeepCells.length)
                return "Invalid Keep location number!";
            if (cellsToAssign.contains(numbers[i] - 1))
                return "repetitious keep locations!";
            cellsToAssign.add(numbers[i] - 1);
        }


        for (int index = 0; index < thisGamePlayers.size(); index++) {
            mapsKeepCells[cellsToAssign.get(index)].flush();

            CastleComponent keep = new CastleComponent(CastleComponentType.KEEP, thisGamePlayers.get(index));
            Man lord = new Soldier(SoldierType.LORD, thisGamePlayers.get(index));

            thisGamePlayers.get(index).setMonarchy(new Monarchy(thisGamePlayers.get(index),
                    colors.get(thisGamePlayers.get(index))));
            thisGamePlayers.get(index).getMonarchy().addBuilding(keep);
            thisGamePlayers.get(index).getMonarchy().addMan(lord);
            mapsKeepCells[cellsToAssign.get(index)].setBuilding(keep);
            mapsKeepCells[cellsToAssign.get(index)].addMan(lord);
        }

        match = new Match(selectedMap, thisGamePlayers);
        return "Start Game";
    }
}