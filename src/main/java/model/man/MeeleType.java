package model.man;

import model.GoodsType;

public enum MeeleType {
    SPEARMAN("SpearMan", 0, 0, 0, null, 8, "european",
            new GoodsType[] {GoodsType.SPEAR}),
    PIKEMAN("PikeMan", 0, 0, 0, null, 20, "european",
            new GoodsType[] {GoodsType.ARMOUR, GoodsType.PIKE}),
    MACEMAN("MaceMan", 0, 0, 0, null, 20, "european",
            new GoodsType[] {GoodsType.LEATHERARMOUR, GoodsType.MACE}),
    SWORDSMAN("SwordsMan", 0, 0, 0, null, 40, "european", requirments),
    KNIGHT("Knight", 0, 0, 0, null, 40, "european", requirments),
    BLACKMONK("BlackMonk", 0, 0, 0, null, 10, "european", null),
    TUNNELER("Tunneler", 0, 0, 0, null, 30, "european", null),
    LADDERMAN("LadderMan", 0, 0, 0, null, 3, "european", null),
    SLAVE("Slave", 0, 0, 0, null, 5, "arab", null),
    ASSASSIN("Assassin", 0, 0, 0, null, 60, "arab", null),
    ARABSWORDSMAN("ArabSwordsMan", 0, 0, 0, null, 80, "arab", null),
    ARCHER("Archer", 0, 0, 0, 0, 12, "european",
            new GoodsType[] {GoodsType.BOW}),
    CROSSBOWMAN("CrossBowMan", 0, 0, 0, 0, 20, "european",
            new GoodsType[] {GoodsType.CROSSBOW, GoodsType.LEATHERARMOUR}),
    ARCHERBOW("ArcherBow", 0, 0, 0, 0, 75, "arab", null),
    SLINGER("Slinger", 0, 0, 0, 0, 12, "arab", null),
    HORSEARCHER("HorseArcher", 0, 0, 0, 0, 60, "arab", null),
    FIRETHROWER("FireThrower", 0, 0, 0, 0, 100, "arab", null);


    private final String name;
    private final int hitpoint;
    private final int damage;
    public final int range;
    private final int movementSpeed;
    private final int cost;
    private final String nationality;
    private final GoodsType[] requirments;
    MeeleType(String name, int hitpoint, int damage, int movementSpeed, Integer range, int cost, String nationality, GoodsType[] requirments) {
        this.name = name;
        this.hitpoint = hitpoint;
        this.damage = damage;
        this.movementSpeed = movementSpeed;
        this.range = range;
        this.cost = cost;
        this.nationality = nationality;
        this.requirments = requirments;
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
}
