package model;

import java.util.HashMap;

public enum MonarchyColorType {
    BLUE("Blue"),
    RED("Red"),
    GREEN("Green"),
    YELLOW("Yellow"),
    BLACK("Black"),
    WHITE("White"),
    CYAN("Cyan"),
    BROWN("Brown");

    private static HashMap<String, MonarchyColorType> map;
    private String colorName;
    MonarchyColorType(String colorName) {
        this.colorName = colorName;
    }

    public String getColorName() {
        return colorName;
    }

    public static void init() {
        for (MonarchyColorType type : MonarchyColorType.values())
            map.put(type.colorName, type);
    }

    public static MonarchyColorType getTypeByName(String name) {
        return map.get(name);
    }
}