package model.building;

import model.GoodsType;
import model.Location;
import model.ProdutionRule;

public enum ProductionBuildingType {
    APPLEFARM("AppleFarm", 100, new int[]{0, 0, 5, 1}, ),
    DAIRYFARM,
    HOPSFARM,
    HUNTINGPOST,
    WHEATFARM,
    BAKERY,
    BREWERY,
    WOODCUTTER,
    MILL,
    QUARRY,
    PITCHRIG,
    IRONMINE,
    ARMOURER,
    POLETURNER,
    FLETCHER,
    BLACKSMITH,
    INN;

    private final String name;
    private final int hitpoint;
    private final int[] resourcesRequiredForBuilding;   // gold, stone, wood, workers.
    private final ProdutionRule[] productionRules;

    ProductionBuildingType(String name, int hitpoint, int[] resourcesRequiredForBuilding, GoodsType resourceGoods, GoodsType[] producedGoods, Location usageRate, ProdutionRule[] productionRules) {
        this.name = name;
        this.hitpoint = hitpoint;
        this.resourcesRequiredForBuilding = resourcesRequiredForBuilding;
        this.productionRules = productionRules;
    }
}