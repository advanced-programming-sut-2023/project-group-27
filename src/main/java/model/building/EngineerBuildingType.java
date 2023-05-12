package model.building;

import model.Movable;

public enum EngineerBuildingType {
    DEZHKOOB("Dezhkoob", 100, true, 20, 500, 200),
    SIEGETOWER("SiegeTower", 100, true, 20, 0, 300),
    FIRETHROWER("FireThrowe", 100, true, 20, 500, 100),
    STATICCATAPLUT("StaticCataplut", 100, false, 20, 500, 300),
    MOBILECATAPLUT("MobileCataplut", 100, true, 20, 500, 200);

    private final String name;
    private final int cost;
    private final boolean isMovable;
    private final int range;
    private final int damage;
    private final int hitpoint;

    EngineerBuildingType(String name, int cost, boolean isMovable, int range, int damage, int hitpoint) {
        this.name = name;
        this.cost = cost;
        this.isMovable = isMovable;
        this.range = range;
        this.damage = damage;
        this.hitpoint = hitpoint;
    }


    public boolean isPassable(Movable movable) {
        return false;
    }

    public int getRange() {
        return range;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public int getDamage() {
        return damage;
    }

    public int getHitpoint() {
        return hitpoint;
    }
}