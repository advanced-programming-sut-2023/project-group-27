package model;

import model.building.Building;
import model.man.Man;

public class Cell implements Passable{
    private LandType type;
    private TreeType treeType;
    private Building building;
    private Man man;
    private int xCoordinate;
    private int yCoordinate;

    public Cell(LandType type, int xCoordinate, int yCoordinate) {
        this.type = type;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
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
        return man;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
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
