package model.building;

import model.Cell;
import model.Destructable;
import model.Location;
import model.Passable;

public class Building extends Destructable implements Passable {
    private boolean isActive;
    private String name;

    private Cell cell;

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

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}