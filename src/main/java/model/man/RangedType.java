package model.man;

public enum RangedType {
    ARCHER("Archer", 0, 0, 0, "european"),
    CROSSBOWMAN("CrossBowMan", 0, 0, 0, "european"),
    ARCHERBOW("ArcherBow", 0, 0, 0, "arab"),
    SLINGER("Slinger", 0, 0, 0, "arab"),
    HORSEARCHER("HorseArcher", 0, 0, 0, "arab"),
    FIRETHROWER("FireThrower", 0, 0, 0, "arab");

    private final String name;
    private final int hitpoint;
    private final int damage;
    private final int range;
    private final String nationality;

    RangedType(String name, int hitpoint, int damage, int range, String nationality) {
        this.name = name;
        this.hitpoint = hitpoint;
        this.damage = damage;
        this.range = range;
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

    public int getRange() {
        return range;
    }

    public String getNationality() {
        return nationality;
    }
}