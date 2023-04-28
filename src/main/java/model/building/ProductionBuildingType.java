package model.building;

import model.ProductionRules;
import model.ProdutionRule;

public enum ProductionBuildingType {
    APPLEFARM("AppleFarm", 100, new int[]{0, 5, 1}, new ProdutionRule[]{ProductionRules.APPLE.getProductionRule()}),
    DAIRYFARM("DairyFarm", 100, new int[]{0, 10, 1}, new ProdutionRule[]{ProductionRules.DAIRY1.getProductionRule(),
            ProductionRules.DAIRY2.getProductionRule()}),
    HOPSFARM("HopsFarm", 100, new int[]{0, 15, 1}, new ProdutionRule[]{ProductionRules.HOPS.getProductionRule()}),
    HUNTINGPOST("HuntingPost", 100, new int[]{0, 5, 1}, new ProdutionRule[]{ProductionRules.HUNTING.getProductionRule()}),
    WHEATFARM("WheatFarm", 100, new int[]{0, 15, 1}, new ProdutionRule[]{ProductionRules.WHEAT.getProductionRule()}),
    BAKERY("Bakery", 100, new int[]{0, 10, 1}, new ProdutionRule[]{ProductionRules.BAKERY.getProductionRule()}),
    BREWERY("Brewery", 100, new int[]{0, 10, 1}, new ProdutionRule[]{ProductionRules.BREWERY.getProductionRule()}),
    WOODCUTTER("WoodCutter", 100, new int[]{0, 3, 1}, new ProdutionRule[]{ProductionRules.WOOD.getProductionRule()}),
    MILL("Mill", 100, new int[]{0, 20, 3}, new ProdutionRule[]{ProductionRules.MILL.getProductionRule()}),
    QUARRY("Quarry", 100, new int[]{0, 20, 3}, new ProdutionRule[]{ProductionRules.STONE.getProductionRule()}),
    PITCHRIG("PitchRig", 100, new int[]{0, 20, 1}, new ProdutionRule[]{ProductionRules.PITCHRIG.getProductionRule()}),
    IRONMINE("IronMine", 100, new int[]{0, 20, 2}, new ProdutionRule[]{ProductionRules.IRONMINE.getProductionRule()}),
    ARMOURER("Armourer", 100, new int[]{100, 20, 1}, new ProdutionRule[]{ProductionRules.ARMOURER.getProductionRule()}),
    POLETURNER("PoluternerWorkShop", 100, new int[]{100, 10, 1}, new ProdutionRule[]{ProductionRules.POLETURNER1.getProductionRule(),
            ProductionRules.POLETURNER2.getProductionRule()}),
    FLETCHER("FletcherWorkShop", 100, new int[]{100, 20, 1}, new ProdutionRule[]{ProductionRules.FLETCHER1.getProductionRule(),
            ProductionRules.FLETCHER2.getProductionRule()}),
    BLACKSMITH("BlackSmith", 100, new int[]{100, 20, 1}, new ProdutionRule[]{ProductionRules.BLACKSMITH1.getProductionRule(),
            ProductionRules.BLACKSMITH2.getProductionRule()}),
    INN("Inn", 100, new int[]{100, 20, 1}, new ProdutionRule[]{ProductionRules.INN.getProductionRule()});

    private final String name;
    private final int hitpoint;
    private final int[] resourcesRequiredForBuilding;   // gold, wood, workers.
    private final ProdutionRule[] productionRules;

    ProductionBuildingType(String name, int hitpoint, int[] resourcesRequiredForBuilding,ProdutionRule[] productionRules) {
        this.name = name;
        this.hitpoint = hitpoint;
        this.resourcesRequiredForBuilding = resourcesRequiredForBuilding;
        this.productionRules = productionRules;
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

    public ProdutionRule[] getProductionRules() {
        return productionRules;
    }
}