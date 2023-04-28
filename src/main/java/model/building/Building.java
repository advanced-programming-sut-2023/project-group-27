package model.building;

import model.Cell;
import model.Destructable;
import model.Passable;

public class Building extends Destructable implements Passable {
    private boolean isActive;
    private String name;
    @Override
    public boolean isPassable() {
        return false;
    }

    public String getName() {
        return name;
    }



}
