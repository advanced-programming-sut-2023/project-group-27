package model;

import java.util.HashMap;
import java.util.Map;

public enum GoodsType {
    APPLE("Apple", 100, 80),
    MEAT("Meat", 300, 220),
    CHEESE("Cheese", 150, 120),
    BREAD("Bread", 80, 60),
    WHEAT("Wheat", 40, 32),
    HOPS("Hops", 50, 40),
    FLOUR("Flour", 30, 22),
    BEER("Beer", 90, 75),
    WOOD("Wood", 20, 16),
    STONE("Stone", 15, 12),
    IRON("Iron", 60, 50),
    BOW("Bow", 60, 50),
    CROSSBOW("CrossBow", 180, 150),
    LEATHERARMOUR("LeatherArmour", 400, 320),
    ARMOUR("Armour", 540, 440),
    SWORD("Sword", 600, 500),
    SPEAR("Spear", 480, 400),
    PIKE("Pike", 350, 280),
    MACE("Mace", 460, 380),
    PITCH("Pitch", 200, 160);


    private static final Map<String, GoodsType> map = new HashMap<>();
    private final String name;
    private final int shopBuyPrice;
    private final int shopCellPrice;

    GoodsType(String name, int shopBuyPrice, int shopCellPrice) {
        this.name = name;
        this.shopBuyPrice = shopBuyPrice;
        this.shopCellPrice = shopCellPrice;
        this.addToMap();
    }

    private void addToMap() {
        map.put(this.name, this);
    }

    public String getName() {
        return name;
    }

    public int getShopBuyPrice() {
        return this.shopBuyPrice;
    }

    public int getShopCellPrice() {
        return shopCellPrice;
    }

    public GoodsType getTypeByName(String name) {
        return map.get(name);
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