package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public enum NaturalEntityType implements Passable {
    DESSERT_SHRUB("DessertPalm", "\u001B[38;5;m", true, "1.png", ""),
    CHERRY_PALM("CherryPalm", "\u001B[31m", true, "2.png", ""),
    OLIVE_TREE("OliveTree", "\u001B[38;5;178m", true, "3.png", ""),
    COCONUT_PALM("CoconutPalm", "\u001B[37m", true, "4.png", ""),
    DATES_PALM("DatesPalm", "\u001B[38;5;237m", true, "5.png", ""),
    ROCK_NORTH("RocktoNorth", "\u001B[38;5;232m", false, "north.png", "n"),
    ROCK_SOUTH("RocktoSouth", "\u001B[38;5;232m", false, "south.png", "s"),
    ROCK_WEST("RocktoWest", "\u001B[38;5;232m", false, "west.png", "w"),
    ROCK_EAST("RocktoEast", "\u001B[38;5;232m", false, "east.png", "e"),
    ROCK_RANDOM("RandomROck", "", false, "", "r");

    private static final HashMap<String, NaturalEntityType> map = new HashMap<>();
    private final String entityName;
    private final String ANSI_COLOR;
    private final boolean passability;
    private final String picture;
    private final String direction;

    public static NaturalEntityType[] getTreeTypes() {
        return new NaturalEntityType[] {DESSERT_SHRUB, CHERRY_PALM, OLIVE_TREE, COCONUT_PALM, DATES_PALM};
    }

    public static NaturalEntityType[] getRockTypes() {
        return new NaturalEntityType[] {ROCK_NORTH, ROCK_EAST, ROCK_SOUTH, ROCK_WEST, ROCK_RANDOM};
    }

    NaturalEntityType(String EntityName, String ANSI_COLOR, boolean passability, String picture, String direction) {
        this.entityName = EntityName;
        this.ANSI_COLOR = ANSI_COLOR;
        this.passability = passability;
        this.picture = picture;
        this.direction = direction;
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

    public ImageView getPicture(Pane pane) {
        Image image = new Image(getClass().getResource("/assets/naturalEntity/" + picture).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.fitHeightProperty().bind(pane.heightProperty());
        imageView.fitWidthProperty().bind(pane.widthProperty());
        return imageView;
    }

    @Override
    public boolean isPassable(Movable movable) {
        return this.passability;
    }

    public String getDirection() {
        return direction;
    }
}
