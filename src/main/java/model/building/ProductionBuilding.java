package model.building;

import model.Production;

import java.util.List;

public class ProductionBuilding extends Building{
    List<Production> requirements;
    Production product;

    public ProductionBuilding(List<Production> requirements, Production product) {
        this.requirements = requirements;
        this.product = product;
    }

    public void act() {
        for (Production requirement : requirements) {
            requirement.utilize();
        }
        product.produce();
    }
}
