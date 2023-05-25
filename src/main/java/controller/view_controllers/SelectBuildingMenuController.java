package controller.view_controllers;

import controller.controller.CoreSelectBuildingMenuController;
import model.building.Building;
import model.castle_components.CastleComponent;
import model.man.SoldierType;
import console_view.SelectBuildingMenu;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String message;
        String typeRegex = "-t\\s+(?<type>\\S+)",
                countRegex = "-c\\s+(?<count>\\d+)";
        Pattern typePattern = Pattern.compile(typeRegex),
                countPattern = Pattern.compile(countRegex);
        Matcher typeMatcher = typePattern.matcher(matcher.group("fields")),
                countMatcher = countPattern.matcher(matcher.group("fields"));
        if (!typeMatcher.find() || !countMatcher.find())
            return "Invalid Fields!\n";

        String type = typeMatcher.group("type");
        int count = Integer.parseInt(countMatcher.group("count"));
        SoldierType soldierType;
        if ((soldierType = SoldierType.getTypeByName(type)) == null)
            return "Invalid troop type!\n";
        if ((soldierType.getNationality().equals("european") &&
                !coreController.getSelectedBuilding().getName().equals("Barracks"))
                || (soldierType.getNationality().equals("arab") &&
                !coreController.getSelectedBuilding().getName().equals("MercenaryPost"))
                || (soldierType.getNationality().equals("guild") &&
                !coreController.getSelectedBuilding().getName().equals("EngineersGuild")))
            return "Can't train this type in this building!";
        return coreController.createUnit(soldierType, count);
    }

    public String repair() {
        if (!(coreController.getSelectedBuilding() instanceof CastleComponent))
            return "This type of building can't be repaired\n";
        return coreController.repair();
    }
}