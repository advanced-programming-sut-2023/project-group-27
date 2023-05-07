package model;

public interface Movable extends Selectable {
    void move(Cell cell, GameMap map);
    Location getLocation();
    Double getMovementSpeed();
}
