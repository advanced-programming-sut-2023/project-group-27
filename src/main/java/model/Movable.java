package model;

public interface Movable extends Selectable {
    void move(Cell cell, GameMap map);
    Double getMovementSpeed();
}
