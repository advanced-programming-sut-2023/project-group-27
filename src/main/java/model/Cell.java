package model;

import model.building.Building;

public class Cell implements Passable{
    private LandType type;
    private Building building;
    private int xCoordinate;
    private int yCoordinate;


    @Override
    public boolean isPassable(Cell cell) {
        return false;
    }
}
