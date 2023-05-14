package model.building;

import model.Cell;
import model.Monarchy;
import model.ProductionRule;
import model.User;

public class ProductionBuilding extends Building{
    private final ProductionBuildingType productionBuildingType;
    private final ProductionRule[] productionRules;
    private final Monarchy monarchy;
    
    public ProductionBuilding(ProductionBuildingType productionType, User owner, Monarchy monarchy, Cell cell) {
        super(productionType.getHitpoint(), owner, cell, productionType.getName());
        this.productionBuildingType = productionType;
        productionRules = productionType.getProductionRules();
        this.monarchy = monarchy;
    }
    
    public ProductionBuildingType getProductionType() {
        return productionBuildingType;
    }

    private boolean canAct(ProductionRule productionRule) {
        return (productionRule.getUsedType() == null) || monarchy.getGood(productionRule.getUsedType()) >= productionRule.getResourceRequired();
    }

    public void act() {
        for (ProductionRule productionRule : productionRules) {
            if (canAct((productionRule))) {
                if (productionRule.getUsedType() != null)
                    monarchy.putGood(productionRule.getUsedType(), monarchy.getGood(productionRule.getUsedType()) - productionRule.getResourceRequired());
                if (productionRule.getProducedType() != null)
                    monarchy.putGood(productionRule.getProducedType(), monarchy.getGood(productionRule.getProducedType()) + productionRule.getResourceProduced());
            }
        }
    }
}