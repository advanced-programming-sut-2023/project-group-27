package model.building;

import model.Production;

import java.util.List;

public class ProductionBuilding extends Building{
    private ProductionBuildingType productionBuildingType;
    private List<Production> requirements;
    private Production product;
    
    public ProductionBuilding(int hitpoint, List<Production> requirements, Production product , ProductionBuildingType productionType) {
        super(hitpoint);
        this.productionBuildingType = productionType;
        this.requirements = requirements;
        this.product = product;
    }
    
    public ProductionBuildingType getProductionType() {
        return productionBuildingType;
    }

    public void act() {
        for (Production requirement : requirements)
            requirement.utilize();
        
        product.produce();
    }
}