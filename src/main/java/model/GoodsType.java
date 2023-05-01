package model;

import java.util.HashMap;
import java.util.Map;

public enum GoodsType {
    APPLE("Apple", 100),
    MEAT("Meat", 300),
    CHEESE("Cheese", 150),
    BREAD("Bread", 80),
    WHEAT("Wheat", 40),
    HOPS("Hops", 50),
    FLOUR("Flour", 30),
    BEER("Beer", 90),
    WOOD("Wood", 20),
    STONE("Stone", 15),
    IRON("Iron", 60),
    BOW("Bow", 60),
    CROSSBOW("CrossBow", 180),
    LEATHERARMOUR("LeatherArmour", 400),
    ARMOUR("Armour", 540),
    SWORD("Sword", 600),
    SPEAR("Spear", 480),
    PIKE("Pike", 350),
    MACE("Mace", 460),
    PITCH("Pitch", 200);


    private static final Map<String, GoodsType> map = new HashMap<>();
    private final String name;
    private final int shopPrice;

    GoodsType(String name, int shopPrice) {
        this.name = name;
        this.shopPrice = shopPrice;
        this.addToMap();
    }

    public void addToMap() {
        map.put(this.name, this);
    }

    public String getName() {
        return name;
    }

    public int getShopPrice() {
        return this.shopPrice;
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