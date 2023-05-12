package model.castle_components;

import model.GoodsType;

import java.util.HashMap;

public enum CastleComponentType {
    KEEP("Keep", 400 , new int[] {80, 80}),
    BIGKEEP("BigKeep", 1600, new int[] {100, 100}),
    WATCHTOWER("WatchTower", 200, new int[] {10, 0}),
    ENVIRTOWER("EnvironmentTower", 200, new int[] {10, 0}),
    DEFENSIVETOWER("DefensiveTower", 200, new int[] {15, 0}),
    SQUARETOWER("SquareTower", 200, new int[] {35, 0}),
    CIRCLETOWER("CircleTower", 200, new int[] {40, 0}),
    CHANGINGBRIDGE("ChangingBridge", 200, new int[] {0, 10});

    private static final HashMap<String, CastleComponentType> map = new HashMap<>();
    private final String name;
    private final int hitpoint;
    private final int[] resourceNeededToBeBuild;  // stone, wood
    CastleComponentType(String name, int hitpoint, int[] costToBeBuild) {
        this.name = name;
        this.hitpoint = hitpoint;
        this.resourceNeededToBeBuild = costToBeBuild;
    }

    public static void init() {
        for (CastleComponentType type : values()) {
            map.put(type.name, type);
        }
    }

    public static CastleComponentType getTypeByName(String input) {
        return map.get(input);
    }
    public String getName() {
        return name;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public int getStoneNeeded() {
        return resourceNeededToBeBuild[0];
    }

    public int getWoodNeeded() {
        return resourceNeededToBeBuild[1];
    }
}