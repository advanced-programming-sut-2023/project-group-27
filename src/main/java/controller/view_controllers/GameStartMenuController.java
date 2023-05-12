package controller.view_controllers;

import controller.controller.CoreGameStartMenuController;

public class GameStartMenuController {
    private final CoreGameStartMenuController coreController;

    public GameStartMenuController(CoreGameStartMenuController coreController) {
        this.coreController = coreController;
    }

    public String showAllUsers() {
        return coreController.showAllUsers();
    }

    public String showAllMaps() {
        return coreController.showAllMaps();
    }

    public String showAllColors() {
        return coreController.showAllColors();
    }

    public String showGamePlayers() {
        return coreController.showGamePlayers();
    }

    public String showSelectedMapInfo() {
        return coreController.showSelectedMapInfo();
    }

    public String navigateSelectedMap() {
        return coreController.navigateSelectedMap();
    }

    public String addPlayer(String input) {
        return coreController.addPlayer(Integer.parseInt(input));
    }

    public String removePlayer(String input) {
        return coreController.removePlayer(Integer.parseInt(input));
    }

    public String selectMap(String input) {
        return coreController.selectMap(Integer.parseInt(input));
    }

    public String resetColors() {
        coreController.resetColors();
        return "all assigned colors are removed.";
    }

    public String setColor(String input) {

    }
}