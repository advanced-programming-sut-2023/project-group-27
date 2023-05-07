package model.man;

import model.*;

public class Man extends Destructable implements Movable {
    private String name;
    private User owner;
    private Location location;

    public Man(int hitpoint, String name, User owner) {
        super(hitpoint);
        this.name = name;
        this.owner = owner;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void move(Cell cell, GameMap map) {
        if (location == null)return;
        Cell origin = map.getCell(location);
        origin.removeMan(this);
        cell.addMan(this);
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }
}