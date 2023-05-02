package model.castle_components;

public enum CastleComponentType {
    ;

    private final String name;
    private final int hitpoint;
    private final int[] costToBeBuild;  // stone, gold

    CastleComponentType(String name, int hitpoint, int[] costToBeBuild) {
        this.name = name;
        this.hitpoint = hitpoint;
        this.costToBeBuild = costToBeBuild;
    }

    public String getName() {
        return name;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public int getStoneNeeded() {
        return costToBeBuild[0];
    }

    public int getWoodNeeded() {
        return costToBeBuild[1];
    }
}