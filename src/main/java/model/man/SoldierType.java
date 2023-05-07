package model.man;

import model.GoodsType;

import java.util.HashMap;

public enum SoldierType {
    SPEARMAN("SpearMan", 0, 0, 0, null, 8, "european",
            new GoodsType[] {GoodsType.SPEAR}),
    PIKEMAN("PikeMan", 0, 0, 0, null, 20, "european",
            new GoodsType[] {GoodsType.ARMOUR, GoodsType.PIKE}),
    MACEMAN("MaceMan", 0, 0, 0, null, 20, "european",
            new GoodsType[] {GoodsType.LEATHER_ARMOUR, GoodsType.MACE}),
    SWORDSMAN("SwordsMan", 0, 0, 0, null, 40, "european",
            new GoodsType[] {GoodsType.ARMOUR, GoodsType.SWORD}),
    KNIGHT("Knight", 0, 0, 0, null, 40, "european",
            new GoodsType[] {GoodsType.ARMOUR, GoodsType.SWORD, GoodsType.HORSE}),
    BLACKMONK("BlackMonk", 0, 0, 0, null, 10, "church", null),
    TUNNELER("Tunneler", 0, 0, 0, null, 30, "guild", null),
    LADDERMAN("LadderMan", 0, 0, 0, null, 3, "guild", null),
    SLAVE("Slave", 0, 0, 0, null, 5, "arab", null),
    ASSASSIN("Assassin", 0, 0, 0, null, 60, "arab", null),
    ARABSWORDSMAN("ArabSwordsMan", 0, 0, 0, null, 80, "arab", null),
    ARCHER("Archer", 0, 0, 0, 0, 12, "european",
            new GoodsType[] {GoodsType.BOW}),
    CROSSBOWMAN("CrossBowMan", 0, 0, 0, 0, 20, "european",
            new GoodsType[] {GoodsType.CROSSBOW, GoodsType.LEATHER_ARMOUR}),
    ARCHERBOW("ArcherBow", 0, 0, 0, 0, 75, "arab", null),
    SLINGER("Slinger", 0, 0, 0, 0, 12, "arab", null),
    HORSEARCHER("HorseArcher", 0, 0, 0, 0, 60, "arab", null),
    FIRETHROWER("FireThrower", 0, 0, 0, 0, 100, "arab", null),
    ENGINEER("Engineer", 0, 0, 0, 0, 40, "guild", null);

    private static final HashMap<String, SoldierType> map = new HashMap<>();
    private final String name;
    private final int hitpoint;
    private final int damage;
    public final int range;
    private final int movementSpeed;
    private final int cost;
    private final String nationality;
    private final GoodsType[] requirements;
    SoldierType(String name, int hitpoint, int damage, int movementSpeed, Integer range, int cost, String nationality, GoodsType[] requirements) {
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
            map.put(type.name, type);
        }
    }
    // TODO remember to init before usage

    public static SoldierType getSoldierTypeByName(String name) {
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

    public int getMovementSpeed() {
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

    public int getRange() {
        return range;
    }
}