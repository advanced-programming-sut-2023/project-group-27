package controller.controller;

import controller.view_controllers.SelectBuildingMenuController;
import model.GoodsType;
import model.Monarchy;
import model.building.Building;
import model.castle_components.CastleComponent;
import model.man.*;
import view.SelectBuildingMenu;

import java.util.Scanner;

public class CoreSelectBuildingMenuController {
    private final Scanner scanner;
    private Building selectedBuilding;
    private Monarchy currentMonarchy;
    private final SelectBuildingMenu selectBuildingMenu;

    public CoreSelectBuildingMenuController(Building selectedBuilding , Scanner scanner, Monarchy currentMonarchy) {
        this.scanner = scanner;
        this.selectedBuilding = selectedBuilding;
        selectBuildingMenu = new SelectBuildingMenu(new SelectBuildingMenuController(selectedBuilding, this));
        this.currentMonarchy = currentMonarchy;
    }

    public void run(){
        selectBuildingMenu.run(scanner);
    }

    public String createUnit(Object troopType, int count) {
        Man[] menToBeAdded = new Man[count];

        for (int i = 0; i < count; i++)
            menToBeAdded[i] = getNewMan(troopType);

        if (menToBeAdded[0] == null) return "";
        currentMonarchy.addallMen(menToBeAdded);
        return "true";
    }

    public Man getNewMan(Object type) {
        if (type == null)
            return new Engineer(100, currentMonarchy.getKing());
        if (type instanceof SoldierType)
            return new Soldier((SoldierType) type, currentMonarchy.getKing());
        return null;
    }

    public String repair() {
        int fullHitpoint = ((CastleComponent) selectedBuilding).getCastleComponentType().getHitpoint(),
                currentStone = currentMonarchy.getStockPile().getGoodsCount(GoodsType.STONE),
                stoneNeeded = fullHitpoint - selectedBuilding.getHitpoint();
        if (currentStone <= stoneNeeded)
            return "Stone needed my Lord\n!";

        currentMonarchy.getStockPile().modifyGoodsCount(GoodsType.STONE, -1 * stoneNeeded);
        selectedBuilding.setHitpoint(fullHitpoint);
        return "Repaired Succesfully!\n";
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }
}