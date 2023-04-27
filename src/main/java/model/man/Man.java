package model.man;

import model.Cell;
import model.Destructable;
import model.Movable;

public class Man extends Destructable implements Movable {
    private String name;

    public Man(int hitpoint, String name) {
        super(hitpoint);
        this.name = name;
    }

    @Override
    public void move(Cell cell) {

    }

    public String getName() {
        return name;
    }
}