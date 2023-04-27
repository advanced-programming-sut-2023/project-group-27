package model;

public enum LandType implements Passable{
    PLAIN("Plain", true, "\\u001B[48;5;231m"),
    GRAVEL("Gravel", true, "\\u001B[48;5;222m"),
    STONE("Stone", true, "\\u001B[48;5;249m"),
    ROCK("Rock", false, "\\u001B[48;5;236m"),
    IRON("Iron", true, "\\u001B[48;5;1m"),
    GRASS("Grass", true, "\\u001B[48;5;2m"),
    MEADOW("Meadow", true, "\\u001B[48;5;10m"),
    DENSEMEADOW("DenseMeadow", true, "\\u001B[48;5;22m"),
    OIl("Oil", true, "\\u001B[48;5;231m"),
    MARSH("Marsh", true, "\\u001B[48;5;3m"),
    SHALLOWWATER("ShallowWater", true, "\\u001B[48;5;75m"),
    RIVER("River", false, "\\u001B[48;5;14m"),
    SMALLLAKE("SmallLake", false, "\\u001B[48;5;33m"),
    LAKE("Lake", false, "\\u001B[48;5;26m"),
    BEACH("Beach", true, "\\u001B[48;5;229m"),
    SEA("Sea", false, "\\u001B[48;5;18m");

    private String typeName;
    private boolean passability;
    private String ANSI_BACKGROUND;

    LandType(String typeName, boolean passability, String ANSI_BACKGROUND) {
        this.typeName = typeName;
        this.passability = passability;
        this.ANSI_BACKGROUND = ANSI_BACKGROUND;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getANSI_BACKGROUND() {
        return ANSI_BACKGROUND;
    }

    @Override
    public boolean isPassable() {
        return this.passability;
    }
}
