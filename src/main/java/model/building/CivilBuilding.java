package model.building;


import model.User;

public class CivilBuilding extends Building {
    private final CivilBuildingType civilBuildingType;

    public CivilBuilding(int hitpoint, CivilBuildingType civilBuildingType, User owner) {
        super(hitpoint, owner);
        this.civilBuildingType = civilBuildingType;
    }

    public CivilBuildingType getCivilType() {
        return civilBuildingType;
    }

}
