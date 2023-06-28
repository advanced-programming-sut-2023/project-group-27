package controller.controller;

import model.LandType;
import model.Location;
import model.Match;
import model.NaturalEntityType;
import model.man.SoldierType;
import server.Connection;

public class CoreMapEditOnline {
    private Connection connection;
    private CoreMapEditMenuController controller;

    public CoreMapEditOnline(Match match, Connection connection) {
        controller = new CoreMapEditMenuController(match, null);
        this.connection = connection;
    }


    public String dropBuilding(Location location, Object key, String type) {
        connection.send("mapEdit dropbuilding -x " + location.x + " -y " + location.y + " -t " + type);
        return controller.dropBuilding(location, key);
    }

    public String dropUnit(Location location, SoldierType soldierType, int count) {
        connection.send("mapEdit dropunit -x " + location.x + " -y " + location.y + " -t " + soldierType + " -c " + count);
        return controller.dropUnit(location, soldierType, count);
    }

    public String setTexture(Location location, LandType landType) {
        connection.send("mapEdit settexture -x " + location.x + " -y " + location.y + " -t " + landType);
        return controller.setTexture(location, landType);
    }

    public String clear(Location location) {
        connection.send("mapEdit clear -x " + location.x + " -y " + location.y);
        return controller.clear(location);
    }

    public String dropTree(Location location, NaturalEntityType treeType) {
        connection.send("mapEdit droptree -x " + location.x + " -y " + location.y + " -t " + treeType);
        return controller.dropTree(location, treeType);
    }

    public String dropRock(Location location, String direction) {
        connection.send("mapEdit droprock -x " + location.x + " -y " + location.y + " -d " + direction);
        return controller.dropRock(location, direction);
    }
}
