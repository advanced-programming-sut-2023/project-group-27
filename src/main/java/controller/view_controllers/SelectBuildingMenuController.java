package controller.view_controllers;

import controller.controller.CoreSelectBuildingMenuController;
import model.building.Building;
import model.castle_components.CastleComponent;
import model.man.SoldierType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectBuildingMenuController {
    private final CoreSelectBuildingMenuController coreController;
    public SelectBuildingMenuController(Building selectedBuilding, CoreSelectBuildingMenuController coreController) {
        this.coreController = coreController;
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
        if ((soldierType = getSoldierType(type)) == null)
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

    private SoldierType getSoldierType(String string) {
        for (SoldierType soldierType : SoldierType.values())
            if (soldierType.getName().equals(string)) return soldierType;
        return null;
    }
}