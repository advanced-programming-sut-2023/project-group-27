package model;

public class ProdutionRule {
    private final GoodsType usedType;
    private final GoodsType producedType;
    private final int turnsNeededToProduce;
    private final int resourceRequired;
    private final int resourceProduced;

    public ProdutionRule(GoodsType usedType, GoodsType producedType, int turnsNeededToProduce, int resourceRequired, int resourceProduced) {
        this.usedType = usedType;
        this.producedType = producedType;
        this.turnsNeededToProduce = turnsNeededToProduce;
        this.resourceRequired = resourceRequired;
        this.resourceProduced = resourceProduced;
    }

    public GoodsType getUsedType() {
        return usedType;
    }

    public GoodsType getProducedType() {
        return producedType;
    }

    public int getTurnsNeededToProduce() {
        return turnsNeededToProduce;
    }

    public int getResourceRequired() {
        return resourceRequired;
    }

    public int getResourceProduced() {
        return resourceProduced;
    }
}