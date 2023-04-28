package model;

public enum ProductionRules {
    APPLE(null, GoodsType.APPLE, 3, 0, 10),
    DAIRY1(null, GoodsType.CHEESE, 3, 0, 10),
    DAIRY2(null, GoodsType.LEATHERARMOUR, 3, 0, 10),
    HOPS,
    HUNTING,
    WHEAT,
    BAKERY,
    BREWERY,
    WOOD,
    MILL,
    STONE,
    PITCHRIG,
    IRONMINE,
    ARMOURER1,
    ARMOURER2,
    POLETURNER1,
    POLETURNER2,
    FLETCHER1,
    FLETCHER2,
    INN,
    BLACKSMITH1,
    BLACKSMITH2;

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
