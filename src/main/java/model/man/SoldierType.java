package model.man;

import model.GoodsType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public enum SoldierType {
    SPEARMAN("SpearMan", 200, 100, 1.2, 3, 8, "european",
            new GoodsType[] {GoodsType.SPEAR}),
    PIKEMAN("PikeMan", 200, 90, 1.0, 0, 20, "european",
            new GoodsType[] {GoodsType.ARMOUR, GoodsType.PIKE}),
    MACEMAN("MaceMan", 200, 80, 2.0, 0, 20, "european",
            new GoodsType[] {GoodsType.LEATHER_ARMOUR, GoodsType.MACE}),
    SWORDSMAN("SwordsMan", 180, 100, 1.0, 0, 40, "european",
            new GoodsType[] {GoodsType.ARMOUR, GoodsType.SWORD}),
    KNIGHT("Knight", 300, 120, 2.5, 0, 40, "european",
            new GoodsType[] {GoodsType.ARMOUR, GoodsType.SWORD, GoodsType.HORSE}),
    BLACKMONK("BlackMonk", 100, 80, 2.2, 0, 10, "church", null),
    TUNNELER("Tunneler", 60, 40, 2.1, 0, 30, "guild", null),
    LADDERMAN("LadderMan", 50, 20, 1.8, 0, 3, "guild", null),
    SLAVE("Slave", 50, 50, 1.5, 0, 5, "arab", null),
    ASSASSIN("Assassin", 120, 90, 3.0, 1, 60, "arab", null),
    ARABSWORDSMAN("ArabSwordsMan", 220, 120, 0.8, 0, 80, "arab", null),
    ARCHER("Archer", 120, 80, 1.5, 10, 12, "european",
            new GoodsType[] {GoodsType.BOW}),
    CROSSBOWMAN("CrossBowMan", 120, 90, 1.2, 10, 20, "european",
            new GoodsType[] {GoodsType.CROSSBOW, GoodsType.LEATHER_ARMOUR}),
    ARCHERBOW("ArcherBow", 100, 100, 1.2, 10, 75, "arab", null),
    SLINGER("Slinger", 80, 120, 1.0, 3, 12, "arab", null),
    HORSEARCHER("HorseArcher", 350, 100, 3.5, 10, 60, "arab", null),
    FIRETHROWER("FireThrower", 120, 80, 1.0, 3, 100, "arab", null),
    ENGINEER("Engineer", 60, 10, 1.5, 0, 40, "guild", null),
    LORD("Lord",400, 100, 1.0 , 0, 0 , "LordShip", null);

    private static final HashMap<String, SoldierType> map = new HashMap<>();
    private final String name;
    private final int hitpoint;
    private final int damage;
    public final Integer range;
    private final Double movementSpeed;
    private final int cost;
    private final String nationality;
    private final GoodsType[] requirements;
    SoldierType(String name, int hitpoint, int damage, Double movementSpeed, Integer range, int cost, String nationality, GoodsType[] requirements) {
        this.name = name;
        this.hitpoint = hitpoint;
        this.damage = damage;
        this.movementSpeed = movementSpeed;
        this.range = range;
        this.cost = cost;
        this.nationality = nationality;
        this.requirements = requirements;
    }

    public static ArrayList<SoldierType> getSpecifiedTroops(String input) {
        ArrayList<SoldierType> output = new ArrayList<>();
        for (SoldierType soldierType : map.values()) {
            if (soldierType.getNationality().equals(input))
                output.add(soldierType);
        }
        return output;
    }
    public static void init() {
        for (SoldierType type : values()) {
            if (type.equals(LORD)) continue;
            map.put(type.name, type);
        }
    }

    public static SoldierType getTypeByName(String name) {
        return map.get(name);
    }

    public String getName() {
        return this.name;
    }

    public int getHitpoint() {
        return this.hitpoint;
    }

    public int getDamage() {
        return this.damage;
    }

    public Double getMovementSpeed() {
        return movementSpeed;
    }

    public String getNationality() {
        return nationality;
    }

    public int getCost() {
        return cost;
    }

    public GoodsType[] getRequirements() {
        return requirements;
    }

    public Integer getRange() {
        return range;
    }
}