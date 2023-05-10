package model.building;

import model.Monarchy;
import model.ProductionRule;
import model.User;

public class ProductionBuilding extends Building{
    private final ProductionBuildingType productionBuildingType;
    private final ProductionRule[] productionRules;
    private final Monarchy monarchy;
    
    public ProductionBuilding(ProductionBuildingType productionType, User owner, Monarchy monarchy) {
        super(productionType.getHitpoint(), owner);
        this.productionBuildingType = productionType;
        productionRules = productionType.getProductionRules();
        this.monarchy = monarchy;
    }
    
    public ProductionBuildingType getProductionType() {
        return productionBuildingType;
    }

    public boolean canAct() {
        for (ProductionRule productionRule : productionRules) {
            if ((productionRule.getUsedType() != null) && monarchy.getGood(productionRule.getUsedType()) < productionRule.getResourceRequired())
                return false;
        }
        return true;
    }
    //TODO use these functions based on counts needed to produce the goods
    public void act() {
        for (ProductionRule productionRule : productionRules) {
            monarchy.putGood(productionRule.getUsedType(), monarchy.getGood(productionRule.getUsedType()) - productionRule.getResourceRequired());
            monarchy.putGood(productionRule.getProducedType(), monarchy.getGood(productionRule.getProducedType()) + productionRule.getResourceProduced());
        }
    }

}