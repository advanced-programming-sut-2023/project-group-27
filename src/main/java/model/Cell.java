package model;

import model.building.Building;
import model.man.Man;

public class Cell implements Passable{
    private LandType type;
    private Building building;
    private Man man;
    private int xCoordinate;
    private int yCoordinate;


    @Override
    public boolean isPassable() {
        if (this.building != null) {
            return building.isPassable();
        }
        return type.isPassable();
    }
}
