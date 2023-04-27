package model.man;

public enum MeeleType {
    SPEARMAN("SpearMan", 0, 0, 0, "european"),
    PIKEMAN("PikeMan", 0 ,0, 0, "european"),
    MACEMAN("MaceMan", 0 ,0, 0, "european"),
    SWORDSMAN("SwordsMan", 0, 0, 0, "european"),
    KNIGHT("Knight", 0, 0, 0, "european"),
    BLACKMONK("BlackMonk", 0, 0, 0, "european"),
    TUNNELER("Tunneler", 0, 0, 0, "european"),
    LADDERMAN("LadderMan", 0, 0, 0, "european"),
    SLAVE("Slave", 0, 0, 0, "arab"),
    ASSASSIN("Assassin", 0, 0, 0, "arab"),
    ARABSWORDSMAN("ArabSwordsMan", 0, 0, 0, "arab");


    private final String name;
    private final int hitpoint;
    private final int damage;
    private final int movementSpeed;
    private final String nationality;
    MeeleType(String name, int hitpoint, int damage, int movementSpeed, String nationality) {
        this.name = name;
        this.hitpoint = hitpoint;
        this.damage = damage;
        this.movementSpeed = movementSpeed;
        this.nationality = nationality;
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
}
