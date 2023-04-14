package model.building;


public class CivilBuilding extends Building {
    private CivilTypes civilTypes;

    public CivilBuilding(CivilTypes civilTypes) {
        this.civilTypes = civilTypes;
    }

    public CivilTypes getCivilTypes() {
        return civilTypes;
    }

}
