package model;

public enum GoodsType {
    APPLE("Apple"),
    MEAT("Meat"),
    CHEESE("Cheese"),
    BREAD("Bread"),
    WHEAT("Wheat"),
    HOPS("Hops"),
    FLOUR("Flour"),
    BEER("Beer"),
    WOOD("Wood"),
    STONE("Stone"),
    IRON("Iron"),
    BOW("Boew"),
    CROSSBOW("CrossBow"),
    LEATHERARMOUR("LeatherArmour"),
    ARMOUR("Armour"),
    SWORD("Sword"),
    PIKE("Pike"),
    MACE("Mace"),
    PITCH("Pitch");


    private String name;

    GoodsType(String name) {
        this.name = name;
    }
}
