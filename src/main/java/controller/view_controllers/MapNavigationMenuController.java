package controller.view_controllers;

import controller.controller.CoreMapNavigationMenuController;
import model.Cell;
import model.LandType;
import model.TreeType;
import model.building.Building;
import model.castle_components.CastleComponent;
import model.man.Engineer;
import model.man.Man;
import model.man.Soldier;

public class MapNavigationMenuController {
    private final CoreMapNavigationMenuController controller;
    public MapNavigationMenuController(int x, int y, CoreMapNavigationMenuController controller) {
        this.controller = controller;
    }

    public void move(int deltaX, int deltaY) {
        controller.move(deltaX, deltaY);
        showMap();
    }

    public void showMap() {
        Cell currentCell;
        String backGroundColor = "", textColor = "", text = " ";
        TreeType treeType;
        Building building;
        LandType type;
        Man man;
        for (int i = Math.min(0, controller.getXCoordinates() - 29);
             i < Math.max(controller.getMapXSize(), controller.getXCoordinates() + 30);
             i++) {
            for (int j =Math.min(0, controller.getYCoordinates() - 29);
                 j < Math.max(controller.getMapYSize(), controller.getYCoordinates() + 30);
                 j++) {
                currentCell = controller.getMapCell(i ,j);
                backGroundColor = ((type = currentCell.getType()) != null) ? type.getANSI_BACKGROUND() : "";
                if ((treeType = currentCell.getTreeType()) != null) {
                    textColor = treeType.getANSI_COLOR();
                    text = "T";
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

                System.out.print(backGroundColor + textColor + text + "\\u001B[0m");
            }
            System.out.print("\n");
        }
    }

    public String showDetails(int x, int y) {
        return null;
    }
}
