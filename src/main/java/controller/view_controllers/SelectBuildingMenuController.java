package controller.view_controllers;

import controller.controller.CoreSelectBuildingMenuController;
import model.building.Building;
import view.SelectBuildingMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SelectBuildingMenuController {
    private final CoreSelectBuildingMenuController coreController;
    private final SelectBuildingMenu menu;
    public SelectBuildingMenuController(CoreSelectBuildingMenuController coreController, Building selectedBuilding, Scanner scanner) {
        this.coreController = coreController;
        this.menu = new SelectBuildingMenu(this, scanner);
    }

    public SelectBuildingMenu getMenu() {
        return menu;
    }

    public String createUnit(Matcher matcher) {
        return  null;
    }

    public String repair() {
        return null;
    }
}
