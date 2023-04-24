package controller.controller;

import controller.view_controllers.SelectBuildingMenuController;
import model.building.Building;
import view.SelectBuildingMenu;

import java.util.Scanner;

public class CoreSelectBuildingMenuController {
    private final Scanner scanner;
    private Building selectedBuilding;
    private final SelectBuildingMenu selectBuildingMenu;

    public CoreSelectBuildingMenuController(Building selectedBuilding , Scanner scanner) {
        this.scanner = scanner;
        this.selectedBuilding = selectedBuilding;
        selectBuildingMenu = new SelectBuildingMenu(new SelectBuildingMenuController(selectedBuilding));
    }

    public void run(){
        selectBuildingMenu.run(scanner);
    }

    public String createUnit(Object troopType, int count) {
        return  null;
    }

    public String repair() {
        return null;
    }
}
