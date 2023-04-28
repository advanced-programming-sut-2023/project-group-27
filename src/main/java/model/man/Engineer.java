package model.man;

import model.Cell;
import model.User;

public class Engineer extends Man {
    public Engineer(int hitpoint, User owner) {
        super(hitpoint, "Engineer", owner);
    }

    public void pourOil(Cell cell) {

    }
}