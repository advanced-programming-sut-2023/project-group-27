package model.man;

import model.Cell;
import model.Location;
import model.User;

public class Engineer extends Man {
    public Engineer(int hitpoint, User owner) {
        super(hitpoint, "Engineer", owner, SoldierType.ENGINEER.getMovementSpeed());
    }

    public void pourOil(Cell cell) {

    }
}