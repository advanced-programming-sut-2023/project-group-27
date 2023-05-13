package model;

import java.util.HashMap;

public enum NaturalEntityType implements Passable {
    DESSERT_SHRUB("DessertPalm", "\u001B[38;5;m", true),
    CHERRY_PALM("CherryPalm", "\u001B[31m", true),
    OLIVE_TREE("OliveTree", "\u001B[38;5;178m", true),
    COCONUT_PALM("CoconutPalm", "\u001B[37m", true),
    DATES_PALM("DatesPalm", "\u001B[38;5;237m", true),
    ROCK_NORTH("RocktoNorth", "\u001B[38;5;232m", false),
    ROCK_SOUTH("RocktoSouth", "\u001B[38;5;232m", false),
    ROCK_WEST("RocktoWest", "\u001B[38;5;232m", false),
    ROCK_EAST("RocktoEast", "\u001B[38;5;232m", false);

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
