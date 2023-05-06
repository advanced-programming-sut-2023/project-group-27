package model;

public enum LandType implements Passable{
    PLAIN("Plain", true, "\\u001B[48;5;231m", true),
    GRAVEL("Gravel", true, "\\u001B[48;5;222m", true),
    STONE("Stone", true, "\\u001B[48;5;249m", true),
    ROCKY("Rocky", false, "\\u001B[48;5;236m", true),
    IRON("Iron", true, "\\u001B[48;5;1m", false),
    GRASS("Grass", true, "\\u001B[48;5;2m", true),
    MEADOW("Meadow", true, "\\u001B[48;5;10m", true),
    DENSEMEADOW("DenseMeadow", true, "\\u001B[48;5;22m", true),
    OIl("Oil", true, "\\u001B[48;5;231m", false),
    MARSH("Marsh", true, "\\u001B[48;5;3m", false),
    SHALLOWWATER("ShallowWater", true, "\\u001B[48;5;75m", false),
    RIVER("River", false, "\\u001B[48;5;14m", false),
    SMALLLAKE("SmallLake", false, "\\u001B[48;5;33m", false),
    LAKE("Lake", false, "\\u001B[48;5;26m", false),
    BEACH("Beach", true, "\\u001B[48;5;229m", false),
    SEA("Sea", false, "\\u001B[48;5;18m", false);

    private final String typeName;
    private final boolean passability;
    private final String ANSI_BACKGROUND;
    private final boolean canPlaceRockOn;


    LandType(String typeName, boolean passability, String ANSI_BACKGROUND, boolean canPlaceRockOn) {
        this.typeName = typeName;
        this.passability = passability;
        this.ANSI_BACKGROUND = ANSI_BACKGROUND;
        this.canPlaceRockOn = canPlaceRockOn;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getANSI_BACKGROUND() {
        return ANSI_BACKGROUND;
    }

    @Override
    public boolean isPassable(Movable movable) {
        return this.passability;
    }

    public boolean canPlaceRockOn() {
        return canPlaceRockOn;
    }

    public boolean canPlaceTreeOn() {
        return canPlaceRockOn && !(this == ROCKY || this == STONE);
    }
}
