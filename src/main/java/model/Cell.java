package model;

import model.building.Building;
import model.man.Man;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Passable{
    private LandType type;
    private TreeType treeType;
    private Building building;
    private Man man;
    private ArrayList<Man> men;
    private Location location;

    public Cell(LandType type, int xCoordinate, int yCoordinate) {
        men = new ArrayList<>();
        this.type = type;
        this.location = new Location(xCoordinate, yCoordinate);
    }

    public Cell(LandType type, Location location) {
        men = new ArrayList<>();
        this.type = type;
        this.location = new Location(location.x, location.y);
    }

    @Override
    public boolean isPassable() {
        if (this.building != null) {
            return building.isPassable();
        }
        return type.isPassable();
    }

    public LandType getType() {
        return type;
    }

    public TreeType getTreeType() {
        return treeType;
    }

    public Building getBuilding() {
        return building;
    }

    public Man getMan() {
        return men.get(0);
    }

    public ArrayList<Man> getMen() {
        return men;
    }

    public void addMan(Man man) {
        men.add(man);
    }

    public void addMen(Man[] menToBeAdded) {
        men.addAll(List.of(menToBeAdded));
    }
    public void removeMan(Man man) {
        men.remove(man);
    }

    public int getXCoordinate() {
        return location.x;
    }

    public int getYCoordinate() {
        return location.y;
    }

    public Location getLocation() {
        return location;
    }

    public void setTreeType(TreeType treeType) {
        this.treeType = treeType;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setMan(Man man) {
        this.man = man;
    }

    public void setType(LandType type) {
        this.type = type;
    }
}