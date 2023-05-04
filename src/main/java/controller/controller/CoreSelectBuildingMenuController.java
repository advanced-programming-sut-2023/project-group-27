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
    private final Building selectedBuilding;
    private final Monarchy currentMonarchy;
    private final SelectBuildingMenu selectBuildingMenu;

    public CoreSelectBuildingMenuController(Building selectedBuilding , Scanner scanner, Monarchy currentMonarchy) {
        this.selectedBuilding = selectedBuilding;
        selectBuildingMenu = new SelectBuildingMenu(new SelectBuildingMenuController(this, selectedBuilding, scanner), scanner);
        this.currentMonarchy = currentMonarchy;
    }

    public void run(){
        String result = "";
        while (!result.equals("Exit")) {
            result = selectBuildingMenu.run();
        }
    }

    public String createUnit(SoldierType troopType, int count) {
        Man[] menToBeAdded = new Man[count];

        for (int i = 0; i < count; i++)
            menToBeAdded[i] = Utilities.getNewMan(troopType, currentMonarchy.getKing());
        if (!evaluateGold(troopType, count)) return "Not enough Gold!\n";
        if (!evaluateOtherRequirements(troopType, count)) return "You are short of armoury or other requirements requirements!\n";
        takeGold(troopType, count);
        takeRequirements(troopType, count);
        currentMonarchy.addAllMen(menToBeAdded);
        selectedBuilding.getCell().addMen(menToBeAdded);
        return "troops added succesfully\n";
    }

    public String repair() {
        int fullHitpoint = ((CastleComponent) selectedBuilding).getCastleComponentType().getHitpoint(),
                currentStone = currentMonarchy.getStockPile().getGoodsCount(GoodsType.STONE),
                stoneNeeded = fullHitpoint - selectedBuilding.getHitpoint();
        if (stoneNeeded == 0 ) return "This building is at maximum hit points\n";
        if (currentStone <= stoneNeeded)
            return "Stone needed my Lord\n!";

        currentMonarchy.getStockPile().modifyGoodsCount(GoodsType.STONE, -1 * stoneNeeded);
        selectedBuilding.setHitpoint(fullHitpoint);
        return "Repaired Successfully!\n";
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public boolean evaluateGold(SoldierType troopType, int count) {
        return currentMonarchy.getGold() >= (troopType.getCost() * count);
    }

    public void takeGold(SoldierType troopType, int count) {
        currentMonarchy.changeGold(-1 * troopType.getCost() * count);
    }

    public boolean evaluateOtherRequirements(SoldierType troopType, int count) {
        GoodsType[] requirments = troopType.getRequirements();
        if (!(requirments == null)) {
            for (GoodsType currentgoods : requirments)
                if (currentMonarchy.getGood(currentgoods) < count) return false;
            return true;
        }
        return false;
    }

    public void takeRequirements(SoldierType troopType, int count) {
        GoodsType[] requirments = troopType.getRequirements();
        if (!(requirments == null)) {
            for (GoodsType currentgoods : requirments)
                currentMonarchy.putGood(currentgoods, currentMonarchy.getGood(currentgoods) - count);
        }
    }
}