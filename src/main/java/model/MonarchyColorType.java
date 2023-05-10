package model;

public enum MonarchyColorType {
    BLUE("Blue"),
    RED("Red"),
    GREEN("Green"),
    YELLOW("Yellow"),
    BLACK("Black"),
    WHITE("White"),
    CYAN("Cyan"),
    BROWN("Brown");

    private String colorName;
    MonarchyColorType(String colorName) {
        this.colorName = colorName;
    }

    public String getColorName() {
        return colorName;
    }
}