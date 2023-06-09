package model;

import java.util.ArrayList;
import java.util.List;

public class Location {
    public int x;
    public int y;

    public Location(int first, int second) {
        this.x = first;
        this.y = second;
    }

    public List<Location> getNeighbors(GameMap map, Movable movable) {
        List<Location> neighbors = new ArrayList<>();
        if (x > 0 && map.getCell(x - 1, y).isPassable(movable)) {
            neighbors.add(map.getCell(x - 1, y).getLocation());
        }
        if (x + 1 < map.getWidth() && map.getCell(x + 1, y).isPassable(movable)) {
            neighbors.add(map.getCell(x + 1, y).getLocation());
        }
        if (y > 0 && map.getCell(x, y - 1).isPassable(movable)) {
            neighbors.add(map.getCell(x, y - 1).getLocation());
        }
        if (y + 1 < map.getHeight() && map.getCell(x, y + 1).isPassable(movable)) {
            neighbors.add(map.getCell(x, y + 1).getLocation());
        }
        return neighbors;
    }

    public int distance(Location location) {
        return Math.abs(x - location.x) + Math.abs(y - location.y);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
  
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        Location location = (Location) o;
        return location.x == this.x && location.y == this.y;
    }

    @Override
    public int hashCode() {
        return x * 1000 + y;
    }

    protected Location getVicintyLocation(int deltaX, int deltaY) {
        return new Location(this.x + deltaX, this.y + deltaY);
    }
}