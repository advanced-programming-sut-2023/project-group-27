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

    public String createUnit(SoldierType troopType, int count) {
        Man[] menToBeAdded = new Man[count];

        for (int i = 0; i < count; i++)
            menToBeAdded[i] = getNewMan(troopType);

        if (menToBeAdded[0] == null) return "Invalid Type\n";
        if (!evaluateGold(troopType, count)) return "Not enough Gold!\n";
        if (!evaluateOtherRequirements(troopType, count)) return "You are short of armoury requirements!\n";
        takeGold(troopType, count);
        takeRequirements(troopType, count);
        currentMonarchy.addallMen(menToBeAdded);
        //TODO add troops on map;
        return "troops added succesfully\n";
    }

    public String repair() {
        int fullHitpoint = ((CastleComponent) selectedBuilding).getCastleComponentType().getHitpoint(),
                currentStone = currentMonarchy.getStockPile().getGoodsCount(GoodsType.STONE),
                stoneNeeded = fullHitpoint - selectedBuilding.getHitpoint();
        if (currentStone <= stoneNeeded)
            return "Stone needed my Lord\n!";

        currentMonarchy.getStockPile().modifyGoodsCount(GoodsType.STONE, -1 * stoneNeeded);
        selectedBuilding.setHitpoint(fullHitpoint);
        return "Repaired Successfully!\n";
    }

    public Man getNewMan(SoldierType type) {
        if (type == null)
            return new Engineer(100, currentMonarchy.getKing());
        return new Soldier(type, currentMonarchy.getKing());
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public boolean evaluateGold(SoldierType troopType, int count) {
        return currentMonarchy.getGold() >= (troopType.getCost() * count);
    }

    public void takeGold(SoldierType troopType, int count) {
        currentMonarchy.changeGold(-1 * (troopType.getCost() * count));
    }

    public boolean evaluateOtherRequirements(SoldierType troopType, int count) {
        GoodsType[] requirments = troopType.getRequirments();
        if (!(requirments == null)) {
            for (GoodsType currentgoods : requirments)
                if (currentMonarchy.getGood(currentgoods) < count) return false;
            return true;
        }
        return false;
    }

    public void takeRequirements(SoldierType troopType, int count) {
        GoodsType[] requirments = troopType.getRequirments();
        if (!(requirments == null)) {
            for (GoodsType currentgoods : requirments)
                currentMonarchy.putGood(currentgoods, currentMonarchy.getGood(currentgoods) - count);
        }
    }
}