package model.building;

import model.*;

public class Building extends Destructable implements Passable {
    private boolean isActive;
    private String name;
    private final User owner;
    private Cell cell;
    private Location location;

    public Building(int hitpoint, User owner) {
        super(hitpoint);
        this.owner = owner;
    }

    @Override
    public boolean isPassable(Movable movable) {
        return false;
    }

    public String getName() {
        return name;
    }

    public Cell getCell() {
        return cell;
    }

    public Location getLocation() {
        return null;
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

    public Location getLocation() {
        return location;
    }
}