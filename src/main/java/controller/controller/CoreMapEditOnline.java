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
        connection.request("mapEdit dropbuilding -x " + location.x + " -y " + location.y + " -t " + type);
        connection.request("refresh -x " + location.x + " -y " + location.y);
        // TODO fix here
        return controller.dropBuilding(location, key);
    }

    public String dropUnit(Location location, SoldierType soldierType, int count) {
        connection.request("mapEdit dropunit -x " + location.x + " -y " + location.y + " -t " + soldierType.getName() + " -c " + count);
        connection.request("refresh -x " + location.x + " -y " + location.y);
        return controller.dropUnit(location, soldierType, count);
    }

    public String setTexture(Location location, LandType landType) {
        connection.request("mapEdit settexture -x " + location.x + " -y " + location.y + " -t " + landType.getTypeName());
        connection.request("refresh -x " + location.x + " -y " + location.y);
        return controller.setTexture(location, landType);
    }

    public String clear(Location location) {
        connection.request("mapEdit clear -x " + location.x + " -y " + location.y);
        connection.request("refresh -x " + location.x + " -y " + location.y);
        return controller.clear(location);
    }

    public String dropTree(Location location, NaturalEntityType treeType) {
        connection.request("mapEdit droptree -x " + location.x + " -y " + location.y + " -t " + treeType.getNaturalEntityName());
        connection.request("refresh -x " + location.x + " -y " + location.y);
        return controller.dropTree(location, treeType);
    }

    public String dropRock(Location location, String direction) {
        connection.request("mapEdit droprock -x " + location.x + " -y " + location.y + " -d " + direction);
        connection.request("refresh -x " + location.x + " -y " + location.y);
        return controller.dropRock(location, direction);
    }
}
