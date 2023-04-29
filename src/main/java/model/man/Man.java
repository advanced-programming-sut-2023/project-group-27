package model.man;

import model.Cell;
import model.Destructable;
import model.Movable;
import model.User;

public class Man extends Destructable implements Movable {
    private String name;
    private User owner;

    public Man(int hitpoint, String name, User owner) {
        super(hitpoint);
        this.name = name;
        this.owner = owner;
    }

    @Override
    public void move(Cell cell) {

    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }
}