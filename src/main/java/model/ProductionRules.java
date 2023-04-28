package model;

public enum ProductionRules {
    APPLE(null, GoodsType.APPLE, 3, 0, 10),
    DAIRY1(null, GoodsType.CHEESE, 3, 0, 10),
    DAIRY2(null, GoodsType.LEATHERARMOUR, 3, 0, 10),
    HOPS(null, GoodsType.HOPS, 3, 0, 10),
    HUNTING(null, GoodsType.MEAT, 3, 0, 10),
    WHEAT(null, GoodsType.WHEAT, 3, 0, 10),
    BAKERY(GoodsType.FLOUR, GoodsType.BREAD, 3, 1, 10),
    BREWERY(null, GoodsType.BEER, 3, 0, 10),
    WOOD(null, GoodsType.WOOD, 3, 0, 10),
    MILL(GoodsType.WHEAT, GoodsType.FLOUR, 3, 1, 10),
    STONE(null, GoodsType.STONE, 3, 0, 10),
    PITCHRIG(null, GoodsType.PITCH, 3, 0, 10),
    IRONMINE(null, GoodsType.IRON, 3, 0, 10),
    ARMOURER(GoodsType.IRON, GoodsType.ARMOUR, 1, 1, 10),
    POLETURNER1(GoodsType.WOOD, GoodsType.SPEAR, 3, 1, 10),
    POLETURNER2(GoodsType.WOOD, GoodsType.PIKE, 3, 1, 10),
    FLETCHER1(GoodsType.WOOD, GoodsType.BOW, 3, 1, 10),
    FLETCHER2(GoodsType.WOOD, GoodsType.CROSSBOW, 3, 1, 10),
    INN(GoodsType.BEER, null, 3, 0, 10),
    BLACKSMITH1(GoodsType.IRON, GoodsType.SWORD, 3, 1, 10),
    BLACKSMITH2(GoodsType.IRON, GoodsType.MACE, 3, 1, 10);

    private final GoodsType usedType;
    private final GoodsType producedType;
    private final int turnsNeededToProduce;
    private final int resourceRequired;
    private final int resourceProduced;

    ProductionRules(GoodsType usedType, GoodsType producedType, int turnsNeededToProduce, int resourceRequired, int resourceProduced) {
        this.usedType = usedType;
        this.producedType = producedType;
        this.turnsNeededToProduce = turnsNeededToProduce;
        this.resourceRequired = resourceRequired;
        this.resourceProduced = resourceProduced;
    }

    public ProdutionRule getProductionRule() {
        return new ProdutionRule(usedType, producedType, turnsNeededToProduce, resourceRequired, resourceProduced);
    }
}
