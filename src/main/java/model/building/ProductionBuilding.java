package model.building;

import model.Production;
import model.ProductionRule;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ProductionBuilding extends Building{
    private ProductionBuildingType productionBuildingType;
    private ArrayList<ProductionRule> productionRules;
    private List<Production> requirements;
    private Production product;
    
    public ProductionBuilding(int hitpoint, List<Production> requirements, Production product , ProductionBuildingType productionType, User owner) {
        super(hitpoint, owner);
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