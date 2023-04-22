package controller.controller;

import controller.view_controllers.SelectBuildingMenuController;
import model.building.Building;
import view.SelectBuildingMenu;

import java.util.Scanner;

public class CoreSelectBuildingMenuController {
    private Building selectedBuilding;
    private final SelectBuildingMenu selectBuildingMenu;

    public CoreSelectBuildingMenuController(Building selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
        selectBuildingMenu = new SelectBuildingMenu(new SelectBuildingMenuController(selectedBuilding));
    }

    public void run(Scanner scanner){
        selectBuildingMenu.run(scanner);
    }

    public String createUnit(Object troopType, int count) {
        return  null;
    }

    public String repair() {
        return null;
    }
}
