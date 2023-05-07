package model.building;

import model.User;
import model.castle_components.CastleComponentType;

import java.util.HashMap;

public enum CivilBuildingType {
    HOVEL("Hovel", 100, new int[] {0, 6, 0}),
    CATHERDAL("Catherdal", 1000, new int[] {1000, 0, 0}),
    CHURCH("Church", 500, new int[] {250, 0, 0}),
    BARRACKS("Barracks", 700, new int[] {0, 0, 15}),
    MERCENARYPOST("MercenaryPost", 500, new int[] {0, 10, 0}),
    ENGINEERSGUILD("EngineersGuild", 400, new int[] {100, 10, 0}),
    STABLE("Stable", 0 , null);

    private static final HashMap<String, CivilBuildingType> map = new HashMap<>();
    private final String name;
    private final int hitpoint;
    private final int[] resourcesNeededToBuild; //gold, wood, stone

    CivilBuildingType(String name, int hitpoint, int[] resourcesNeededToBuild) {
        this.name = name;
        this.hitpoint = hitpoint;
        this.resourcesNeededToBuild = resourcesNeededToBuild;
        addToMap();
    }

    private void addToMap() {
        map.put(this.name, this);
    }

    public static CivilBuildingType getTypeByName(String input) {
        return map.get(input);
    }

    public String getName() {
        return name;
    }

    public int getGoldNeeded() {
        return this.resourcesNeededToBuild[0];
    }

    public int getWoodNeeded() {
        return this.resourcesNeededToBuild[1];
    }

    public int getStoneNeeded() {
        return this.resourcesNeededToBuild[2];
    }
    public int getHitpoint() {
        return this.hitpoint;
    }

    public CivilBuilding createCivilBuilding(User owner) {
        return new CivilBuilding(hitpoint, this, owner);
    }
}
