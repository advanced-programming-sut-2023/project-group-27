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
    BOW("Bow"),
    CROSSBOW("CrossBow"),
    LEATHERARMOUR("LeatherArmour"),
    ARMOUR("Armour"),
    SWORD("Sword"),
    SPEAR("Spear"),
    PIKE("Pike"),
    MACE("Mace"),
    PITCH("Pitch"),
    HORSE("Horse");


    private final String name;

    GoodsType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static GoodsType[] getGranaryGoods() {
        return new GoodsType[] {BREAD, APPLE, CHEESE, MEAT};
    }

    public static GoodsType[] getStockPileGoods() {
        return new GoodsType[] {WHEAT, HOPS, FLOUR, BEER, WOOD, STONE, IRON, PITCH};
    }

    public static GoodsType[] getArmouryGoods() {
        return new GoodsType[] {BOW, CROSSBOW, PIKE, MACE, SWORD, SPEAR, LEATHERARMOUR};
    }

    public static GoodsType[] getGoods() {
        return new GoodsType[] {APPLE, MEAT, CHEESE, BREAD, WHEAT, HOPS, FLOUR,
                BEER, WOOD, STONE, IRON, BOW, CROSSBOW, LEATHERARMOUR, ARMOUR, SWORD,
                SPEAR, PIKE, MACE, PITCH};
    }
}