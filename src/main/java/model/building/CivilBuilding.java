package model.building;


public class CivilBuilding extends Building {
    private final CivilType civilType;

    public CivilBuilding(CivilType civilType) {
        this.civilType = civilType;
    }

    public CivilType getCivilType() {
        return civilType;
    }

}
