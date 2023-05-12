package model.building;

import model.Movable;

import java.util.HashMap;
import java.util.Map;

public enum EngineerBuildingType {
    DEZHKOOB("battering ram", 100, true, 20, 500, 200),
    SIEGETOWER("siege Tower", 100, true, 20, 0, 300),
    FIRETHROWER("fire thrower", 100, true, 20, 500, 100),
    STATICCATAPLUT("static catapult", 100, false, 20, 500, 300),
    MOBILECATAPLUT("mobile catapult", 100, true, 20, 500, 200),
    SHIELD("shield", 100 , false ,0 , 0 , 400);

    private static final Map<String , EngineerBuildingType> map = new HashMap<>();
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

    public static void init() {
        for (EngineerBuildingType type : values()) {
            map.put(type.name , type);
        }
    }


    public boolean isPassable(Movable movable) {
        return false;
    }

    public static EngineerBuildingType getTypeByName(String name) {
        return map.get(name);
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