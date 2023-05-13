package model.man;

import model.*;

public class Engineer extends Man {
    public Engineer(int hitpoint, User owner) {
        super(hitpoint, "Engineer", owner, SoldierType.ENGINEER.getMovementSpeed());
    }

    public void pourOil(Cell cell) {

    }

    public Destructable getDestructable() {
        return this;
    }
}