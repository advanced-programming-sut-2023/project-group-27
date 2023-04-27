package model.man;

import model.Cell;
import model.Destructable;
import model.Movable;

public class Man extends Destructable implements Movable {
    private String name;
    @Override
    public void move(Cell cell) {

    }

    public String getName() {
        return name;
    }
}