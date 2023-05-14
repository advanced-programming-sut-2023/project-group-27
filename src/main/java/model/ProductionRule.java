package model;

public class ProductionRule {
    private final GoodsType usedType;
    private final GoodsType producedType;
    private final int resourceRequired;
    private final int resourceProduced;

    public ProductionRule(GoodsType usedType, GoodsType producedType, int resourceRequired, int resourceProduced) {
        this.usedType = usedType;
        this.producedType = producedType;
        this.resourceRequired = resourceRequired;
        this.resourceProduced = resourceProduced;
    }

    public GoodsType getUsedType() {
        return usedType;
    }

    public GoodsType getProducedType() {
        return producedType;
    }

    public int getResourceRequired() {
        return resourceRequired;
    }

    public int getResourceProduced() {
        return resourceProduced;
    }
}