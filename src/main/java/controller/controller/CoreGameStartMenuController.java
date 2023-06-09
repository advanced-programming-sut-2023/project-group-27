package controller.controller;

import controller.view_controllers.GameStartMenuController;
import model.*;
import model.castle_components.CastleComponent;
import model.castle_components.CastleComponentType;
import model.man.Man;
import model.man.Soldier;
import model.man.SoldierType;
import console_view.GameStartMenu;

import java.io.IOException;
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

    public void run() throws IOException {
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
            output += "\n" + (index + 1) + "- name:" + user.getUsername() + " aka: " + user.getNickname() + " highscore: " + user.getHighScore();
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
                    + (keepCells.containsKey(user) ? keepCells.get(user).getLocation() : "-");
        }
        return output;
    }

    public String showSelectedMapInfo() {
        if (selectedMap != null) {
            int i = 0;
            String output = "name: " + selectedMap.getMapName() + " capacity: " + selectedMap.getCapacity() + "\nkeep positions:";
            for (Cell cell : selectedMap.getKeepsLocations()) {
                i++;
                output += "\n" + i + "- " + cell.getLocation().toString();
            }
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

    public String assignKeepsAndStart(ArrayList<Integer> numbers) {
        if (colors.size() != thisGamePlayers.size())
            return "set all players' colors first.";
        if (selectedMap == null)
            return "select a map first!";
        if (numbers.size() != thisGamePlayers.size())
            return "invalid numbers. can't match to players.";
        if (thisGamePlayers.size() <= 1 || thisGamePlayers.size() > selectedMap.getCapacity())
            return "bad players count";
        Cell[] mapsKeepCells = selectedMap.getKeepsLocations();
        ArrayList<Integer> cellsToAssign = new ArrayList<>();

        for (int i = 0; i < thisGamePlayers.size(); i++) {
            if (numbers.get(i) <= 0 || numbers.get(i) > mapsKeepCells.length)
                return "Invalid Keep location number!";
            if (cellsToAssign.contains(numbers.get(i) - 1))
                return "repetitious keep locations!";
            cellsToAssign.add(numbers.get(i) - 1);
        }


        for (int index = 0; index < thisGamePlayers.size(); index++) {
            //mapsKeepCells[cellsToAssign.get(index)].flush();

            User user = thisGamePlayers.get(index);
            CastleComponent keep = new CastleComponent(CastleComponentType.KEEP, user, mapsKeepCells[cellsToAssign.get(index)]);
            Man lord = new Soldier(SoldierType.LORD, user);
            // TODO remove extra men
            //Man swordsMan = new Soldier(SoldierType.SWORDSMAN, user);
            //Man archer = new Soldier(SoldierType.ARCHER, user);

            user.setMonarchy(new Monarchy(user,
                    colors.get(user), selectedMap, mapsKeepCells[cellsToAssign.get(index)].getLocation()));
            Monarchy monarchy = user.getMonarchy();
            monarchy.addBuilding(keep);
            monarchy.addMan(lord);
            //monarchy.addMan(swordsMan);
            //monarchy.addMan(archer);
            monarchy.setLord(lord);
            monarchy.changeGold(1200);
            monarchy.putGood(GoodsType.WOOD, 100);
            monarchy.putGood(GoodsType.STONE, 100);
            monarchy.putGood(GoodsType.APPLE, 500);
            monarchy.putGood(GoodsType.CHEESE, 500);
            mapsKeepCells[cellsToAssign.get(index)].setBuilding(keep);
            mapsKeepCells[cellsToAssign.get(index)].addMan(lord);
            int x = mapsKeepCells[cellsToAssign.get(index)].getLocation().x;
            int y = mapsKeepCells[cellsToAssign.get(index)].getLocation().y;
            //selectedMap.getCell(x, y-1).addMan(swordsMan);
            //selectedMap.getCell(x, y+1).addMan(archer);
        }

        match = new Match(selectedMap, thisGamePlayers);
        StrongholdCrusader.setCurrentMatch(match);
        return "Start Game";
    }

    public GameMap[] getAllMaps() {
        return allMaps;
    }

    public MonarchyColorType[] getColorTypes() {
        return colorTypes;
    }

    public ArrayList<User> getThisGamePlayers() {
        return thisGamePlayers;
    }

    public HashMap<User, MonarchyColorType> getColors() {
        return colors;
    }

    public HashMap<User, Cell> getKeepCells() {
        return keepCells;
    }

    public User[] getAllUsers() {
        return allUsers;
    }

    public GameMap getSelectedMap() {
        return selectedMap;
    }

    public Match getMatch() {
        return match;
    }
}