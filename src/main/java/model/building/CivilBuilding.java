package model.building;


public class CivilBuilding extends Building {
    private final CivilType civilType;

    public CivilBuilding(int hitpoint, CivilType civilType) {
        super(hitpoint);
        this.civilType = civilType;
    }

    public CivilType getCivilType() {
        return civilType;
    }

}
