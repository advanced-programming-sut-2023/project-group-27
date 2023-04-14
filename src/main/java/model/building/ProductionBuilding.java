package model.building;

import model.Production;

import java.util.List;

public class ProductionBuilding extends Building{
    private ProductionTypes productionTypes;

    public ProductionBuilding(List<Production> requirements, Production product , ProductionTypes productionTypes) {
        this.productionTypes = productionTypes;
        this.requirements = requirements;
        this.product = product;
    }
    public ProductionTypes getProductionTypes() {
        return productionTypes;
    }

    List<Production> requirements;
    Production product;

    public void act() {
        for (Production requirement : requirements) {
            requirement.utilize();
        }
        product.produce();
    }
}
