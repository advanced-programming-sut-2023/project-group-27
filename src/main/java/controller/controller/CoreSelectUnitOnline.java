package controller.controller;

import model.GameMap;
import model.Match;
import model.Selectable;
import model.man.SoldierType;
import server.Connection;

import java.util.ArrayList;
import java.util.List;

public class CoreSelectUnitOnline {
    private Connection connection;
    private CoreSelectUnitMenuController controller;

    public CoreSelectUnitOnline(ArrayList<Selectable> selectedUnits, Match match, GameMap mapData, SoldierType type, Connection connection) {
        this.connection = connection;
        controller = new CoreSelectUnitMenuController(selectedUnits , match , null ,
                match.getCurrentUser() , mapData , type);
    }

    public void moveTo(int xCoordinate, int yCoordinate) {
        for (Selectable selectable : controller.getTheSelected()) {
            connection.send("selectunit -x " + selectable.getLocation().x + " -y "
                    + selectable.getLocation().y + " -h " + selectable.hashCode() +
                    " move unit to -x " + xCoordinate + " -y " + yCoordinate);
        }
        controller.moveTo(xCoordinate, yCoordinate);
    }

    public void attackByEnemy(int xCoordinate, int yCoordinate) {
        for (Selectable selectable : controller.getTheSelected()) {
            connection.send("selectunit -x " + selectable.getLocation().x + " -y "
                    + selectable.getLocation().y + " -h " + selectable.hashCode() +
                    " attack -e " + xCoordinate + " " + yCoordinate);
        }
        controller.attackByEnemy(xCoordinate, yCoordinate);
    }

    public void patrol(int xCoordinate, int yCoordinate, int xCoordinate1, int yCoordinate1) {
        for (Selectable selectable : controller.getTheSelected()) {
            connection.send("selectunit -x " + selectable.getLocation().x + " -y "
                    + selectable.getLocation().y + " -h " + selectable.hashCode()
                    + " patrol unit -x1 " + xCoordinate + " -y1 " + yCoordinate + " -x2 "
                    + xCoordinate1 + " -y2 " + yCoordinate1);
        }
        controller.patrol(xCoordinate, yCoordinate, xCoordinate1, yCoordinate1);
    }

    public void attackByXY(int xCoordinate, int yCoordinate) {
        for (Selectable selectable : controller.getTheSelected()) {
            connection.send("selectunit -x " + selectable.getLocation().x + " -y "
                    + selectable.getLocation().y + " -h " + selectable.hashCode() +
                    " attack -x " + xCoordinate + " -y " + yCoordinate);
        }
        controller.attackByXY(xCoordinate, yCoordinate);
    }
}
