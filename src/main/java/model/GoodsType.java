package model;

import java.util.HashMap;
import java.util.Map;

public enum GoodsType {
    APPLE("Apple", 100, 80, 20000),
    MEAT("Meat", 300, 220, 10000),
    CHEESE("Cheese", 150, 120, 30000),
    BREAD("Bread", 80, 60, 25000),
    WHEAT("Wheat", 40, 32, 100000),
    HOPS("Hops", 50, 40, 70000),
    FLOUR("Flour", 30, 22, 120000),
    BEER("Beer", 90, 75, 50000),
    WOOD("Wood", 20, 16, 30000),
    STONE("Stone", 15, 12, 15000),
    IRON("Iron", 60, 50, 30000),
    BOW("Bow", 60, 50, 1000),
    CROSSBOW("CrossBow", 180, 150, 800),
    LEATHER_ARMOUR("LeatherArmour", 400, 320, 800),
    ARMOUR("Armour", 540, 440, 700),
    SWORD("Sword", 600, 500, 500),
    SPEAR("Spear", 480, 400, 700),
    PIKE("Pike", 350, 280, 700),
    MACE("Mace", 460, 380, 600),
    PITCH("Pitch", 200, 160, 400),
    HORSE("Horse", 0, 0, 0);


    private static final Map<String, GoodsType> map = new HashMap<>();
    private final String name;
    private final int shopBuyPrice;
    private final int shopSellPrice;
    private final int storageLimit;

    GoodsType(String name, int shopBuyPrice, int shopSellPrice, int storageLimit) {
        this.name = name;
        this.shopBuyPrice = shopBuyPrice;
        this.shopSellPrice = shopSellPrice;
        this.storageLimit = storageLimit;
    }

    public static void init() {
        for (GoodsType type : values()) {
            map.put(type.name, type);
        }
    }

    public String getName() {
        return name;
    }

    public int getShopBuyPrice() {
        return this.shopBuyPrice;
    }

    public int getShopSellPrice() {
        return shopSellPrice;
    }

    public int getStorageLimit() {
        return storageLimit;
    }

    public static GoodsType getTypeByName(String name) {
        return map.get(name);
    }

    public static GoodsType[] getGranaryGoods() {
        return new GoodsType[] {BREAD, APPLE, CHEESE, MEAT};
    }

    public static GoodsType[] getStockPileGoods() {
        return new GoodsType[] {WHEAT, HOPS, FLOUR, BEER, WOOD, STONE, IRON, PITCH};
    }

    public static GoodsType[] getArmouryGoods() {
        return new GoodsType[] {BOW, CROSSBOW, PIKE, MACE, SWORD, SPEAR, LEATHER_ARMOUR};
    }

    public static GoodsType[] getGoods() {
        return new GoodsType[] {APPLE, MEAT, CHEESE, BREAD, WHEAT, HOPS, FLOUR,
                BEER, WOOD, STONE, IRON, BOW, CROSSBOW, LEATHER_ARMOUR, ARMOUR, SWORD,
                SPEAR, PIKE, MACE, PITCH};
    }
}