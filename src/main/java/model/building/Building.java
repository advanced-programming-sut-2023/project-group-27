package model.building;

import model.Cell;
import model.Destructable;
import model.Passable;

public class Building extends Destructable implements Passable {
    private boolean isActive;
    private String name;

    public Building(int hitpoint) {
        super(hitpoint);
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    public String getName() {
        return name;
    }



}
