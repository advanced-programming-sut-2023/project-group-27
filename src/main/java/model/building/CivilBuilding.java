package model.building;


import model.Cell;
import model.User;

public class CivilBuilding extends Building {
    private final CivilBuildingType civilBuildingType;

    public CivilBuilding(int hitpoint, CivilBuildingType civilBuildingType, User owner, Cell cell) {
        super(hitpoint, owner, cell, civilBuildingType.getName());
        this.civilBuildingType = civilBuildingType;
    }

    public CivilBuildingType getCivilType() {
        return civilBuildingType;
    }

}
