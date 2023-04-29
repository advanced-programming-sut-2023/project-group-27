package model.building;

public enum CivilType {
    ;

    private int hitpoint;

    CivilType() {
    }

    public CivilBuilding createCivilBuilding() {
        return new CivilBuilding(hitpoint, this);
    }
}
