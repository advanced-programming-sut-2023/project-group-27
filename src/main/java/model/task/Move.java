package model.task;

import controller.controller.BFS;
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
        this.movable.move(map.getCell(path.get(Math.min(range, path.size() - 1))), map);
        for (int i = 0; i < Math.min(range, path.size() - 1); i++) {
            path.removeFirst();
        }
    }

    public boolean isValid() {
        if (this.path == null || this.path.size() <= 1) {
            return false;
        }
        return true;
    }

     public Move(GameMap map, Movable movable, int destinationX, int destinationY) {
        this.movementSpeed = movable.getMovementSpeed();
        Location origin = movable.getLocation();
        Location destination = new Location(destinationX, destinationY);
        this.map = map;
        this.bfs = new BFS(map, movable);
        this.path = this.bfs.pathTo(destination);
        this.movable = movable;
    }
}
