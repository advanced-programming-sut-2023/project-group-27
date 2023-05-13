package model.building;

import model.Cell;
import model.User;

public class EngineerBuilding extends Building{
    public EngineerBuilding(EngineerBuildingType type, User owner, Cell cell) {
        super(type.getHitpoint(), owner, cell, type.getName());
    }
}
