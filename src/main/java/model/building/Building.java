package model.building;

import model.Cell;
import model.Destructable;
import model.Passable;

public class Building extends Destructable implements Passable {
    private boolean isActive;

    @Override
    public boolean isPassable(Cell cell) {
        return false;
    }
}
