package model;

public interface Movable {
    public void move(Cell cell, GameMap map);
    public Location getLocation();
}
