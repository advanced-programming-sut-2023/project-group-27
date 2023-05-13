package controller.view_controllers;

import controller.controller.CoreMapEditMenuController;
import model.LandType;
import model.Location;
import model.NaturalEntityType;
import model.man.SoldierType;

import java.util.HashMap;

public class MapEditMenuController {
    private HashMap<String, String> dataHoldingUtility;
    private final CoreMapEditMenuController coreController;

    public MapEditMenuController(CoreMapEditMenuController coreController) {
        this.coreController = coreController;
    }

    public String setTexture(String input) {
        if ((dataHoldingUtility = Utilities.extractInputs(input)) == null)
            return "repetitive inputs are inserted!";
        LandType landType = LandType.getLandTypeByName(dataHoldingUtility.get("t"));
        if (landType == null)
            return "Invalid LandType name!";
        if (!dataHoldingUtility.containsKey("x1")) {
            try {
                return coreController.setTexture(
                        new Location(Integer.parseInt(dataHoldingUtility.get("x")),
                                Integer.parseInt(dataHoldingUtility.get("y"))),
                        landType);
            } catch (Exception ex) {
                return "You have provided invalid inputs for coordinates.";
            }
        }

        try {
            return coreController.setTexture(
                    new Location(Integer.parseInt(dataHoldingUtility.get("x1")),
                    Integer.parseInt(dataHoldingUtility.get("y1"))),
                    new Location(Integer.parseInt(dataHoldingUtility.get("x2")), Integer.parseInt(dataHoldingUtility.get("y2"))),
                    landType);
        } catch (Exception ex) {
            return "You have provided invalid inputs for coordinates.";
        }
    }

    public String clear(String input) {
        if ((dataHoldingUtility = Utilities.extractInputs(input)) == null)
            return "repetitive inputs are inserted!";

        try {
            return coreController.clear(
                    new Location(Integer.parseInt(dataHoldingUtility.get("x")),
                    Integer.parseInt(dataHoldingUtility.get("y"))));
        } catch (Exception ex) {
            return "You have provided invalid inputs for coordinates.";
        }
    }

    public String dropRock(String input) {
        if ((dataHoldingUtility = Utilities.extractInputs(input)) == null)
            return "repetitive inputs are inserted!";
        try {
            return coreController.dropRock(
                    new Location(Integer.parseInt(dataHoldingUtility.get("x")),
                    Integer.parseInt(dataHoldingUtility.get("y"))),
                    dataHoldingUtility.get("d"));
        } catch (Exception ex) {
            return "You have provided invalid inputs for coordinates.";
        }
    }

    public String dropTree(String input) {
        if ((dataHoldingUtility = Utilities.extractInputs(input)) == null)
            return "repetitive inputs are inserted!";
        NaturalEntityType naturalEntityType = NaturalEntityType.getNaturalEntityTypeByName(dataHoldingUtility.get("t"));
        if (naturalEntityType == null)
            return "Invalid TreeType name!";

        try {
            return coreController.dropTree(
                    new Location(Integer.parseInt(dataHoldingUtility.get("x")),
                    Integer.parseInt(dataHoldingUtility.get("y"))), naturalEntityType);
        } catch (Exception ex) {
            return "You have provided invalid inputs for coordinates.";
        }
    }

    public String dropUnit(String input) {
        if ((dataHoldingUtility = Utilities.extractInputs(input)) == null)
            return "repetitive inputs are inserted!";
        SoldierType soldierType = SoldierType.getTypeByName(dataHoldingUtility.get("t"));
        if (soldierType == null)
            return "Invalid unit name!";

        try {
            return coreController.dropUnit(
                    new Location(Integer.parseInt(dataHoldingUtility.get("x")),
                    Integer.parseInt(dataHoldingUtility.get("y"))),
                    soldierType,
                    Integer.parseInt(dataHoldingUtility.get("c")));
        } catch (Exception ex) {
            return "You have provided invalid inputs for coordinates and count.";
        }
    }

    public String dropBuilding(String input) {
        if ((dataHoldingUtility = Utilities.extractInputs(input)) == null)
            return "repetitive inputs are inserted!";
        Object buildingType = Utilities.getBuildingTypeByName(dataHoldingUtility.get("t"));

        if (buildingType == null)
            return "Invalid building type!";

        try {
            return coreController.dropBuilding(
                    new Location(Integer.parseInt(dataHoldingUtility.get("x")),
                    Integer.parseInt(dataHoldingUtility.get("y"))),
                    buildingType);
        } catch (Exception ex) {
            return "You have provided invalid inputs for coordinates.";
        }
    }
}