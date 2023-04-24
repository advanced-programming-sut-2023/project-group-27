package controller.view_controllers;

import controller.controller.CoreSelectBuildingMenuController;
import model.building.Building;

import java.util.regex.Matcher;

public class SelectBuildingMenuController {
    private final CoreSelectBuildingMenuController coreController;
    public SelectBuildingMenuController(Building selectedBuilding, CoreSelectBuildingMenuController coreController) {
        this.coreController = coreController;
    }

    public String createUnit(Matcher matcher) {
        return  null;
    }

    public String repair() {
        return null;
    }
}
