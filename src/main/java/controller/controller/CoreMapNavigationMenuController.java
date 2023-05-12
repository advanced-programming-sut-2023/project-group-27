package controller.controller;

import controller.view_controllers.MapNavigationMenuController;
import model.Cell;
import model.GameMap;
import model.Location;
import model.man.Man;
import view.MapNavigationMenu;

import java.util.Scanner;

public class CoreMapNavigationMenuController {
    private final Location location;
    private final GameMap gameMap;

    private final MapNavigationMenu mapNavigationMenu;
    public CoreMapNavigationMenuController(int x, int y, Scanner scanner, GameMap gameMap) {
        MapNavigationMenuController mapNavigationMenuController = new MapNavigationMenuController(x, y, this);
        this.mapNavigationMenu = new MapNavigationMenu(mapNavigationMenuController, scanner);
        this.location = new Location(x, y);
        this.gameMap = gameMap;
    }

    public int getXCoordinates() {
        return location.x;
    }

    public int getYCoordinates() {
        return location.y;
    }

    public int getMapXSize() {
        return gameMap.getWidth();
    }

    public int getMapYSize() {
        return gameMap.getHeight();
    }

    public Cell getMapCell(int x, int y) {
        return gameMap.getCell(x ,y);
    }
    public String run(){
        String mapNavigationMenuResult;
        while (true) {
            mapNavigationMenuResult = mapNavigationMenu.run();
            if (mapNavigationMenuResult.equals("Exit")) {
                return "Exit";
            }
        }
    }

    public void move(int deltaX, int deltaY) {
        location.x = Math.min(Math.max(0, location.x + deltaX), getMapXSize() - 1);
        location.y = Math.min(Math.max(0, location.y + deltaY), getMapYSize() - 1);
    }

    public String showDetails(int x, int y) {
        Cell cell = gameMap.getCell(x ,y);
        String output = "Cell Details:\n";
        output += "Land Type: " + cell.getType().getANSI_BACKGROUND() + cell.getType().getTypeName() + "\u001B[0m\n";
        if (cell.getNaturalEntityType() != null)
            output += "NaturalEntity(Tree or Rocks) Type: " + cell.getNaturalEntityType().getANSI_COLOR() + cell.getNaturalEntityType().getNaturalEntityName() + "\u001B[0m\n";
        if (cell.getBuilding() != null)
            output += "Building Type: " + cell.getBuilding().getName() + " owner: " + cell.getBuilding().getOwner().getUsername() + " hitpoints: " + cell.getBuilding().getHitpoint() + "\n";
        if (cell.getMen() != null && cell.getMen().size() != 0)
            for (Man man : cell.getMen())
                output += "Human Type: " + man.getName() + " owner: " + man.getOwner() + " hitpoints: " + man.getHitpoint() + "\n";
        return output;
    }
}