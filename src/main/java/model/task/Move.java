package model.task;

import controller.controller.BFS;
import graphics_view.view.animations.MoveAnimation;
import model.GameMap;
import model.Location;
import model.Movable;

import java.util.LinkedList;

public class Move extends Task {
    private BFS bfs;
    private final GameMap map;
    private LinkedList<Location> path;
    private double reminder;
    private final double movementSpeed;
    private final Movable movable;

    public Move(GameMap map, Movable movable, int destinationX, int destinationY) {
        this.movementSpeed = movable.getMovementSpeed();
        Location origin = movable.getLocation();
        Location destination = new Location(destinationX, destinationY);
        this.map = map;
        this.bfs = new BFS(map, movable);
        this.path = this.bfs.pathTo(destination);
        for (Location location: new Location(destinationX, destinationY).getNeighbors(map, movable)) {
            if (path == null) {
                this.path = bfs.pathTo(location);
            }
        }
        this.movable = movable;
    }

    @Override
    public void run() {
        int range = (int) Math.floor(movementSpeed+reminder);
        reminder = movementSpeed + reminder - range;
        for (int i = 1; i <= Math.min(range, path.size() - 1); i++) {
            if (!map.getCell(path.get(i)).isPassable(movable)) {
                Location origin = path.get(0);
                Location destination = path.get(path.size()-1);
                this.bfs = new BFS(map, movable);
                this.path = this.bfs.pathTo(destination);
                if (this.path == null) {
                    return;
                }
                break;
            }
        }
        Location location = path.get(Math.min(range, path.size() - 1));
        this.movable.move(map.getCell(location), map);
        for (int i = 0; i < Math.min(range, path.size() - 1); i++) {
            path.removeFirst();
        }
    }

    public boolean isValid() {
        if (movable.getDestructable().getHitpoint() <= 0) {
            return false;
        }
        if (this.path == null || this.path.size() <= 1) {
            return false;
        }
        return true;
    }

    public Movable getOwner() {
        return movable;
    }

    @Override
    public String toString() {
        return "Move";
    }
}
