package controller.controller;

import controller.view_controllers.MapNavigationMenuController;
import model.Cell;
import model.GameMap;
import model.Location;
import view.MapNavigationMenu;

import java.util.Scanner;

public class CoreMapNavigationMenuController {
    private Location location;
    private GameMap gameMap;

    private final MapNavigationMenu mapNavigationMenu;
    private final MapNavigationMenuController mapNavigationMenuController;
    private final CoreGameMenuController coreGameMenuController;
    public CoreMapNavigationMenuController(int x, int y, Scanner scanner, GameMap gameMap, CoreGameMenuController coreGameMenuController) {
        this.mapNavigationMenuController = new MapNavigationMenuController(x, y, this);
        this.coreGameMenuController = coreGameMenuController;
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
            if (mapNavigationMenuResult.equals("Exit"))
                coreGameMenuController.run();
            }
    }

    public void move(int deltaX, int deltaY) {
        location.x += deltaX;
        location.y += deltaY;
    }

    public String showDetails(int x, int y) {
        return null;
    }
}