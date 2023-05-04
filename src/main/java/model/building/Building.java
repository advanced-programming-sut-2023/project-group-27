package model.building;

import model.*;

public class Building extends Destructable implements Passable {
    private boolean isActive;
    private String name;
    private final User owner;
    private Cell cell;

    public Building(int hitpoint, User owner) {
        super(hitpoint);
        this.owner = owner;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public User getOwner() {
        return owner;
    }
}