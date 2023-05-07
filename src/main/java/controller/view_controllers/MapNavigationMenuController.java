package controller.view_controllers;

import controller.controller.CoreMapNavigationMenuController;
import model.Cell;
import model.LandType;
import model.NaturalEntityType;
import model.building.Building;
import model.castle_components.CastleComponent;
import model.man.Engineer;
import model.man.Man;
import model.man.Soldier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapNavigationMenuController {
    private final CoreMapNavigationMenuController controller;
    public MapNavigationMenuController(int x, int y, CoreMapNavigationMenuController controller) {
        this.controller = controller;
    }

    public void move(String input) {
        String regex = "(?<direction>((up)|(down)|(left)|(right)))(\\s+(?<number>-?\\d+))?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        int deltaX = 0, deltaY = 0, displacement = 0;
        String result;
        while (matcher.find()) {
            displacement = ((result = matcher.group("number")) == null) ? 1 : Integer.parseInt(result);
            switch (matcher.group("direction")) {
                case "up":
                    deltaY += displacement;
                    break;
                case "down":
                    deltaY -= displacement;
                    break;
                case "left":
                    deltaX -= displacement;
                    break;
                case "right":
                    deltaX += displacement;
                    break;
            }
        }
        controller.move(deltaX, deltaY);
        showMap();
    }

    public void showMap() {
        Cell currentCell;
        String backGroundColor = "", textColor = "", text = " ";
        NaturalEntityType naturalEntityType;
        Building building;
        LandType type;
        Man man;
        for (int i = Math.max(0, controller.getXCoordinates() - 29);
             i < Math.min(controller.getMapXSize(), controller.getXCoordinates() + 30);
             i++) {
            for (int j =Math.max(0, controller.getYCoordinates() - 29);
                 j < Math.min(controller.getMapYSize(), controller.getYCoordinates() + 30);
                 j++) {
                currentCell = controller.getMapCell(i ,j);
                backGroundColor = ((type = currentCell.getType()) != null) ? type.getANSI_BACKGROUND() : "";
                if ((naturalEntityType = currentCell.getNaturalEntityType()) != null) {
                    textColor = naturalEntityType.getANSI_COLOR();
                    text = naturalEntityType.isPassable(null) ? "T" : "R";
                }
                if ((building = currentCell.getBuilding()) != null) {
                    if (building instanceof CastleComponent) text = "W";
                    else text = "B";
                }
                if ((man = currentCell.getMan()) != null) {
                    if (man instanceof Soldier) text = "S";
                    else if (man instanceof Engineer) text = "E";
                    else text = "H";
                }

                System.out.print(backGroundColor + textColor + text + "\u001B[0m");
            }
            System.out.print("\n");
        }
    }

    public String showDetails(String input) {
        String regex = "(?<coordinate>((-x)|(-y)))\\s*(?<number>-?\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String axis = "";
        int x = 0, y = 0, number;
        while (matcher.find()) {
            if (axis.equals(matcher.group("coordinate")))
                return "Repetitious coordinates, include both -x and -y.";
            axis = matcher.group("coordinate");
            number = Integer.parseInt(matcher.group("number"));

            if (number < 0)
                return "Coordinates can not be negative.";

            switch (axis) {
                case "-x":
                    x = number;
                    break;
                case "-y":
                    y = number;
                    break;
            }
        }

        if ((x >= controller.getMapXSize()) || (y >= controller.getMapYSize()))
            return "Coordinates bigger than map size.";

        return controller.showDetails(x, y);
    }
}
