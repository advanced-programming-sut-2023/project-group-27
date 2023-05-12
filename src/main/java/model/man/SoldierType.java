package model.man;

import model.GoodsType;

import java.util.HashMap;

public enum SoldierType {
    SPEARMAN("SpearMan", 30, 0, 0d, null, 8, "european",
            new GoodsType[] {GoodsType.SPEAR}),
    PIKEMAN("PikeMan", 40, 0, 0d, null, 20, "european",
            new GoodsType[] {GoodsType.ARMOUR, GoodsType.PIKE}),
    MACEMAN("MaceMan", 50, 0, 0d, null, 20, "european",
            new GoodsType[] {GoodsType.LEATHER_ARMOUR, GoodsType.MACE}),
    SWORDSMAN("SwordsMan", 50, 10, 1d, null, 40, "european",
            new GoodsType[] {GoodsType.ARMOUR, GoodsType.SWORD}),
    KNIGHT("Knight", 70, 20, 1d, null, 40, "european",
            new GoodsType[] {GoodsType.ARMOUR, GoodsType.SWORD, GoodsType.HORSE}),
    BLACKMONK("BlackMonk", 29, 5, 1d, null, 10, "church", null),
    TUNNELER("Tunneler", 20, 0, 1d, null, 30, "guild", null),
    LADDERMAN("LadderMan", 10, 0, 1d, null, 3, "guild", null),
    SLAVE("Slave", 0, 0, 1d, null, 5, "arab", null),
    ASSASSIN("Assassin", 50, 25, 1d, null, 60, "arab", null),
    ARABSWORDSMAN("ArabSwordsMan", 20, 14, 1d, null, 80, "arab", null),
    ARCHER("Archer", 0, 10, 1.5, 13, 12, "european",
            new GoodsType[] {GoodsType.BOW}),
    CROSSBOWMAN("CrossBowMan", 50, 12, 1d, 11, 20, "european",
            new GoodsType[] {GoodsType.CROSSBOW, GoodsType.LEATHER_ARMOUR}),
    ARCHERBOW("ArcherBow", 60, 11, 0d, 8, 75, "arab", null),
    SLINGER("Slinger", 0, 6, 1d, 8, 12, "arab", null),
    HORSEARCHER("HorseArcher", 60, 12, 1d, 8, 60, "arab", null),
    FIRETHROWER("FireThrower", 0, 9, 1d, 8, 100, "arab", null),
    ENGINEER("Engineer", 0, 20, 1.5, null, 40, "guild", null),
    LORD("Lord",150, 40, 1.0 , null, 0 , "LordShip", null);

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