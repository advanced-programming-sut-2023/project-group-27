package controller.controller;

import controller.view_controllers.SelectBuildingMenuController;
import model.building.Building;
import view.SelectBuildingMenu;

import java.util.Scanner;

public class CoreSelectBuildingMenuController {
    private final SelectBuildingMenuController buildingController;
    private Building selectedBuilding;
    private final SelectBuildingMenu selectBuildingMenu;

    public CoreSelectBuildingMenuController(Building selectedBuilding , Scanner scanner) {
        this.selectedBuilding = selectedBuilding;
        this.buildingController = new SelectBuildingMenuController(this, selectedBuilding, scanner);
        this.selectBuildingMenu = this.buildingController.getMenu();
    }

    public void run(){
        selectBuildingMenu.run();
    }

    public String createUnit(Object troopType, int count) {
        return  null;
    }

    public String repair() {
        return null;
    }
}
