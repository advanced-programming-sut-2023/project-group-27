package model.man;

import model.*;

public class Man extends Destructable implements Movable {
    private String name;
    private User owner;
    private Location location;

    public Man(int hitpoint, String name, User owner, Location location) {
        super(hitpoint);
        this.name = name;
        this.owner = owner;
        this.location = location;
    }

    @Override
    public void move(Cell cell) {

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