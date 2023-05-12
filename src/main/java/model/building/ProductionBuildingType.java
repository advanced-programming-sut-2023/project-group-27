package model.building;

import model.GoodsType;
import model.ProductionRules;
import model.ProductionRule;
import model.castle_components.CastleComponentType;

import java.util.HashMap;

public enum ProductionBuildingType {
    APPLEFARM("AppleFarm", 200, new int[]{30, 5, 1}, new ProductionRule[]{ProductionRules.APPLE.getProductionRule()}),
    DAIRYFARM("DairyFarm", 100, new int[]{30, 10, 1}, new ProductionRule[]{ProductionRules.DAIRY1.getProductionRule(),
            ProductionRules.DAIRY2.getProductionRule()}),
    HOPSFARM("HopsFarm", 200, new int[]{40, 15, 1}, new ProductionRule[]{ProductionRules.HOPS.getProductionRule()}),
    HUNTINGPOST("HuntingPost", 300, new int[]{20, 5, 1}, new ProductionRule[]{ProductionRules.HUNTING.getProductionRule()}),
    WHEATFARM("WheatFarm", 100, new int[]{50, 15, 1}, new ProductionRule[]{ProductionRules.WHEAT.getProductionRule()}),
    BAKERY("Bakery", 100, new int[]{60, 10, 1}, new ProductionRule[]{ProductionRules.BAKERY.getProductionRule()}),
    BREWERY("Brewery", 100, new int[]{30, 10, 1}, new ProductionRule[]{ProductionRules.BREWERY.getProductionRule()}),
    WOODCUTTER("WoodCutter", 200, new int[]{40, 3, 1}, new ProductionRule[]{ProductionRules.WOOD.getProductionRule()}),
    MILL("Mill", 150, new int[]{60, 20, 3}, new ProductionRule[]{ProductionRules.MILL.getProductionRule()}),
    QUARRY("Quarry", 100, new int[]{20, 20, 3}, new ProductionRule[]{ProductionRules.STONE.getProductionRule()}),
    PITCHRIG("PitchRig", 100, new int[]{40, 20, 1}, new ProductionRule[]{ProductionRules.PITCHRIG.getProductionRule()}),
    IRONMINE("IronMine", 180, new int[]{60, 20, 2}, new ProductionRule[]{ProductionRules.IRONMINE.getProductionRule()}),
    ARMOURER("Armourer", 170, new int[]{100, 20, 1}, new ProductionRule[]{ProductionRules.ARMOURER.getProductionRule()}),
    POLETURNER("PoluternerWorkShop", 200, new int[]{100, 10, 1}, new ProductionRule[]{ProductionRules.POLETURNER1.getProductionRule(),
            ProductionRules.POLETURNER2.getProductionRule()}),
    FLETCHER("FletcherWorkShop", 400, new int[]{100, 20, 1}, new ProductionRule[]{ProductionRules.FLETCHER1.getProductionRule(),
            ProductionRules.FLETCHER2.getProductionRule()}),
    BLACKSMITH("BlackSmith", 300, new int[]{100, 20, 1}, new ProductionRule[]{ProductionRules.BLACKSMITH1.getProductionRule(),
            ProductionRules.BLACKSMITH2.getProductionRule()}),
    INN("Inn", 500, new int[]{100, 20, 1}, new ProductionRule[]{ProductionRules.INN.getProductionRule()});

    private static final HashMap<String, ProductionBuildingType> map = new HashMap<>();
    private final String name;
    private final int hitpoint;
    private final int[] resourcesRequiredForBuilding;   // gold, wood, workers.
    private final ProductionRule[] productionRules;

    ProductionBuildingType(String name, int hitpoint, int[] resourcesRequiredForBuilding, ProductionRule[] productionRules) {
        this.name = name;
        this.hitpoint = hitpoint;
        this.resourcesRequiredForBuilding = resourcesRequiredForBuilding;
        this.productionRules = productionRules;
    }

    public static void init() {
        for (ProductionBuildingType type : values()) {
            map.put(type.name, type);
        }
    }
    // TODO remember to init before usage

    public static ProductionBuildingType getTypeByName(String input) {
        return map.get(input);
    }

    public String getName() {
        return name;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public int getGoldRequired() {
        return resourcesRequiredForBuilding[0];
    }

    public int getWoodRequired() {
        return resourcesRequiredForBuilding[1];
    }

    public int getWorkersRequired() {
        return resourcesRequiredForBuilding[2];
    }

    public ProductionRule[] getProductionRules() {
        return productionRules;
    }
}