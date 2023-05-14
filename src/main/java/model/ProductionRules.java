package model;

public enum ProductionRules {
    APPLE(null, GoodsType.APPLE, 0, 10),
    DAIRY1(null, GoodsType.CHEESE, 0, 10),
    DAIRY2(null, GoodsType.LEATHER_ARMOUR, 0, 10),
    HOPS(null, GoodsType.HOPS, 0, 5),
    HUNTING(null, GoodsType.MEAT, 0, 4),
    WHEAT(null, GoodsType.WHEAT, 0, 5),
    BAKERY(GoodsType.FLOUR, GoodsType.BREAD, 1, 5),
    BREWERY(null, GoodsType.BEER, 0, 10),
    WOOD(null, GoodsType.WOOD, 0, 10),
    MILL(GoodsType.WHEAT, GoodsType.FLOUR, 1, 5),
    STONE(null, GoodsType.STONE, 0, 10),
    PITCHRIG(null, GoodsType.PITCH, 0, 10),
    IRONMINE(null, GoodsType.IRON, 0, 10),
    ARMOURER(GoodsType.IRON, GoodsType.ARMOUR, 1, 2),
    POLETURNER1(GoodsType.WOOD, GoodsType.SPEAR, 1, 2),
    POLETURNER2(GoodsType.WOOD, GoodsType.PIKE, 1, 2),
    FLETCHER1(GoodsType.WOOD, GoodsType.BOW, 1, 2),
    FLETCHER2(GoodsType.WOOD, GoodsType.CROSSBOW, 1, 2),
    INN(GoodsType.BEER, null, 5, 0),
    BLACKSMITH1(GoodsType.IRON, GoodsType.SWORD,1, 2),
    BLACKSMITH2(GoodsType.IRON, GoodsType.MACE, 1, 2);

    private final GoodsType usedType;
    private final GoodsType producedType;
    private final int resourceRequired;
    private final int resourceProduced;

    ProductionRules(GoodsType usedType, GoodsType producedType, int resourceRequired, int resourceProduced) {
        this.usedType = usedType;
        this.producedType = producedType;
        this.resourceRequired = resourceRequired;
        this.resourceProduced = resourceProduced;
    }

    public ProductionRule getProductionRule() {
        return new ProductionRule(usedType, producedType, resourceRequired, resourceProduced);
    }
}