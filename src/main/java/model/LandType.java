package model;

import javafx.scene.paint.Color;

import java.util.HashMap;

public enum LandType implements Passable{
    PLAIN("Plain", true, "\u001B[48;5;234m", true, Color.rgb(224,200, 200)),
    GRAVEL("Gravel", true, "\u001B[48;5;222m", true, Color.rgb(124,115, 115)),
    STONE("Stone", true, "\u001B[48;5;249m", true, Color.rgb(212,234, 212)),
    ROCKY("Rocky", false, "\u001B[48;5;236m", true, Color.rgb(103,100, 100)),
    IRON("Iron", true, "\u001B[48;5;1m", false, Color.rgb(63,4, 8)),
    GRASS("Grass", true, "\u001B[48;5;2m", true, Color.rgb(2,248, 92)),
    MEADOW("Meadow", true, "\u001B[48;5;10m", true, Color.rgb(35,168, 241)),
    DENSEMEADOW("DenseMeadow", true, "\u001B[48;5;22m", true, Color.rgb(4,84, 86)),
    OIl("Oil", true, "\u001B[48;5;231m", false, Color.rgb(253,255, 155)),
    MARSH("Marsh", true, "\u001B[48;5;3m", false, Color.rgb(16,39, 58)),
    SHALLOWWATER("ShallowWater", true, "\u001B[48;5;75m", false, Color.rgb(0,0, 255)),
    RIVER("River", false, "\u001B[48;5;14m", false, Color.rgb(0,0, 255)),
    SMALLLAKE("SmallLake", false, "\u001B[48;5;33m", false, Color.rgb(0,0, 255)),
    LAKE("Lake", false, "\u001B[48;5;26m", false, Color.rgb(0,0, 255)),
    BEACH("Beach", true, "\u001B[48;5;229m", false, Color.rgb(0,0, 255)),
    SEA("Sea", false, "\u001B[48;5;18m", false, Color.rgb(0,0, 255));

    private static final HashMap<String, LandType> map = new HashMap<>();
    private final String typeName;
    private final boolean passability;
    private final String ANSI_BACKGROUND;
    private final boolean canPlaceRockOn;
    private final Color color;

    LandType(String typeName, boolean passability, String ANSI_BACKGROUND, boolean canPlaceRockOn, Color color) {
        this.typeName = typeName;
        this.passability = passability;
        this.ANSI_BACKGROUND = ANSI_BACKGROUND;
        this.canPlaceRockOn = canPlaceRockOn;
        this.color = color;
    }

    public static void init() {
        for (LandType type : values()) {
            map.put(type.typeName, type);
        }
    }

    public static LandType getLandTypeByName(String input) {
        return map.get(input);
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

    public Color getColor() {
        return color;
    }
}