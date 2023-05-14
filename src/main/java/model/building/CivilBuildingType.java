package model.building;

import model.Cell;
import model.User;

import java.util.HashMap;

public enum CivilBuildingType {
    HOVEL("Hovel", 100, new int[] {0, 30, 0}),
    CATHEDRAL("Cathedral", 1000, new int[] {100, 100, 100}),
    CHURCH("Church", 500, new int[] {25, 30, 10}),
    BARRACKS("Barracks", 700, new int[] {50, 10, 15}),
    MERCENARY_POST("MercenaryPost", 500, new int[] {80, 10, 5}),
    ENGINEERS_GUILD("EngineersGuild", 400, new int[] {100, 10, 15}),
    STABLE("Stable", 1000 , new int[] {20, 50, 20});

    private static final HashMap<String, CivilBuildingType> map = new HashMap<>();
    private final String name;
    private final int hitpoint;
    private final int[] resourcesNeededToBuild; //gold, wood, stone

    CivilBuildingType(String name, int hitpoint, int[] resourcesNeededToBuild) {
        this.name = name;
        this.hitpoint = hitpoint;
        this.resourcesNeededToBuild = resourcesNeededToBuild;
    }

    public static void init() {
        for (CivilBuildingType type : values()) {
            map.put(type.name, type);
        }
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

    public CivilBuilding createCivilBuilding(User owner, Cell cell) {
        return new CivilBuilding(hitpoint, this, owner, cell);
    }
}