package model.building;


public class CivilBuilding extends Building {
    private final CivilBuildingType civilBuildingType;

    public CivilBuilding(int hitpoint, CivilBuildingType civilBuildingType) {
        super(hitpoint);
        this.civilBuildingType = civilBuildingType;
    }

    public CivilBuildingType getCivilType() {
        return civilBuildingType;
    }

}
