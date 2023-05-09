package model;

import java.util.HashMap;

public enum NaturalEntityType implements Passable {
    DESSERT_SHRUB("Dessert Palm", "\u001B[38;5;m", true),
    CHERRY_PALM("Cherry Palm", "\u001B[31m", true),
    OLIVE_TREE("Olive Tree", "\u001B[38;5;178m", true),
    COCONUT_PALM("Coconut Palm", "\u001B[37m", true),
    DATES_PALM("Dates Palm", "\u001B[38;5;237m", true),
    ROCK_NORTH("Rock to North", "\u001B[38;5;232m", false),
    ROCK_SOUTH("Rock to North", "\u001B[38;5;232m", false),
    ROCK_WEST("Rock to North", "\u001B[38;5;232m", false),
    ROCK_EAST("Rock to North", "\u001B[38;5;232m", false);

    private static final HashMap<String, NaturalEntityType> map = new HashMap<>();
    private final String entityName;
    private final String ANSI_COLOR;
    private final boolean passability;

    NaturalEntityType(String EntityName, String ANSI_COLOR, boolean passability) {
        this.entityName = EntityName;
        this.ANSI_COLOR = ANSI_COLOR;
        this.passability = passability;
    }

    public static void init() {
        for (NaturalEntityType type : values()) {
            map.put(type.entityName, type);
        }
    }
    // TODO remember to init before usage

    public static NaturalEntityType getNaturalEntityTypeByName(String input) {
        return map.get(input);
    }

    public String getANSI_COLOR() {
        return ANSI_COLOR;
    }

    public String getNaturalEntityName() {
        return entityName;
    }


    @Override
    public boolean isPassable(Movable movable) {
        return this.passability;
    }
}
