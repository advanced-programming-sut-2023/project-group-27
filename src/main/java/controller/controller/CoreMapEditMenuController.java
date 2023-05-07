package controller.controller;

import controller.view_controllers.MapEditMenuController;
import model.*;
import model.building.*;
import model.castle_components.CastleComponent;
import model.castle_components.CastleComponentType;
import model.man.Man;
import model.man.SoldierType;
import view.MapEditMenu;

import java.util.Scanner;

public class CoreMapEditMenuController {
    private final Scanner scanner;
    private final MapEditMenu mapEditMenu;

    private final Match currentMatch;
    private final GameMap currentMap;
    private User currentOwner;

    public CoreMapEditMenuController(Match match, Scanner scanner) {
        this.currentMatch = match;
        this.currentMap = match.getCurrentMatchMap();
        this.currentOwner = match.getCurrentMonarchy().getKing();
        this.scanner = scanner;
        mapEditMenu = new MapEditMenu(new MapEditMenuController(this));
    }

    public void run(){
        mapEditMenu.run(scanner);
    }

    public String setTexture(Location location, LandType landType) {
        if (!Utilities.checkBounds(location, currentMap))
            return "Location out of bounds!";
        if (!Utilities.checkTextureChangePermission(currentMap.getCell(location.x, location.y)))
            return "A building, tree or person is in this location. can't edit texture.";
        currentMap.getCell(location.x, location.y).setType(landType);
        return "Texture changed successfully!";
    }

    public String setTexture(Location location1, Location location2, LandType landType) {
        if (!Utilities.checkBounds(location1, currentMap))
            return "Location 1 is out of bounds!";
        if (!Utilities.checkBounds(location2, currentMap))
            return "Location 2 is out of bounds!";
        if (!Utilities.checkRectanglePoints(location1, location2, currentMap))
            return "Invalid Locations for Rectangle points!";
        String output = "";
        for (int i = location1.x; i <= location1.y; i++) {
            for (int j = location2.x; j <= location2.y; j++) {
                if (!Utilities.checkTextureChangePermission(currentMap.getCell(i, j))) {
                    output += "There's  building, tree or person in x: " + i + " and y: " + j + ". can't change the texture!\n";
                    continue;
                }
                currentMap.getCell(i, j).setType(landType);
            }
        }
        return output + "Texture of locations not specified above have successfully changed!";
    }

    public String clear(Location location) {
        if (!Utilities.checkBounds(location, currentMap))
            return "Location out of bounds!";
        Cell cellToBeCleared = currentMap.getCell(location.x, location.y);
        cellToBeCleared.clear();
        return "Location cleared successfully!";
    }

    public String dropRock(Location location, String direction) {
        if (!Utilities.checkBounds(location, currentMap))
            return "Location out of bounds!";
        Cell currentCell = currentMap.getCell(location.x, location.y);

        if (currentCell.getMen().size() != 0)
            return "Can't put rock here. there are people in this location. clear them first.";
        if (!currentCell.getType().canPlaceRockOn())
            return "You can't put rock in here and places such as water or marsh.";
        String output = "";

        if (currentCell.getNaturalEntityType() != null) {
            if (currentCell.getNaturalEntityType().isPassable(null))
                output = "There's a tree in this location. It'll be replaced with rock.\n";
            else output = "There's a Rock in this location. It'll be replaced with new rock.\n";
        }

        switch (direction) {
            case "n":
                currentCell.setNaturalEntityType(NaturalEntityType.ROCKNORTH);
                break;
            case "e":
                currentCell.setNaturalEntityType(NaturalEntityType.ROCKEAST);
                break;
            case "w":
                currentCell.setNaturalEntityType(NaturalEntityType.ROCKWEST);
                break;
            case "s":
                currentCell.setNaturalEntityType(NaturalEntityType.ROCKSOUTH);
                break;
            case "r":
                currentCell.setNaturalEntityType(Utilities.getRandomRock());
                break;
            default:
                return "Invalid rock directon.";
        }

        return output + "Rock placed successfully!";
    }

    public String dropTree(Location location, NaturalEntityType naturalEntityType) {
        if (!Utilities.checkBounds(location, currentMap))
            return "Location out of bounds!";
        Cell currentCell = currentMap.getCell(location.x, location.y);

        if (currentCell.getMen().size() != 0)
            return "Can't put tree here. there are people in this location. clear them first.";
        if (!currentCell.getType().canPlaceTreeOn())
            return "You can't put tree in here and places such as water or marsh.";
        String output = "";

        if (currentCell.getNaturalEntityType() != null) {
            if (currentCell.getNaturalEntityType().isPassable(null))
                output = "There's a tree in this location. It'll be replaced with new tree.\n";
            else output = "There's a Rock in this location. It'll be replaced with tree.\n";
        }

        currentCell.setNaturalEntityType(naturalEntityType);
        return output + "Tree placed successfully!";
    }

    public String dropUnit(Location location, SoldierType soldierType, int count) {
        if (!Utilities.checkBounds(location, currentMap))
            return "Location out of bounds!";
        Cell currentCell = currentMap.getCell(location);

        if (!currentCell.isPassable(Utilities.getNewMan(soldierType, currentOwner)))
            return "Can't place soldier or person here.";
        if (count <= 0 || count > 30)
            return "Count isn't in bounds.";

        Man[] men = new Man[count];
        for (int i = 0; i < count; i++) {
            men[i] = Utilities.getNewMan(soldierType, currentOwner);
            men[i].setLocation(location);
        }
        currentMatch.getCurrentMonarchy().addAllMen(men);
        currentCell.addMen(men);

        return "Men added Successfully";
    }

    public String dropBuilding(Location location,Object type) {
        if (!Utilities.checkBounds(location, currentMap))
            return "Location out of bounds!";
        Cell currentCell = currentMap.getCell(location.x, location.y);
        if (!currentCell.isPassable(null))
            return "Can't place building here!";

        Building building;
        if (type instanceof CivilBuildingType)
            building = new CivilBuilding(((CivilBuildingType) type).getHitpoint(), (CivilBuildingType) type, currentOwner);
        else if (type instanceof ProductionBuildingType)
            building = new ProductionBuilding(0, null, null, (ProductionBuildingType) type, currentOwner);
            //TODO fix this shit
        else
            //(type instanceof CastleComponentType)
            building = new CastleComponent(((CastleComponentType) type).getHitpoint(), (CastleComponentType) type, currentOwner );

        currentCell.setBuilding(building);
        currentMatch.getCurrentMonarchy().addBuilding(building);
        return "Building placed successfully";
    }
}