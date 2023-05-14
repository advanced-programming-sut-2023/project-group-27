package model.task;

import controller.controller.BFS;
import model.GameMap;
import model.Location;
import model.Movable;

import java.util.LinkedList;

public class Patrol extends Task {
    private final Location destination1, destination2;
    private LinkedList<Location> initialPath, patrolPath;
    private final Movable movable;
    private final GameMap map;
    double reminder, movementSpeed;
    private Boolean isInitialized = false;

    @Override
    public void run() {
        int range = (int) Math.floor(movementSpeed+reminder);
        reminder = movementSpeed + reminder - range;
        LinkedList<Location> path;
        if (!isInitialized) {
            path = initialPath;
        } else {
            path = patrolPath;
        }

        for (int i = 1; i <= Math.min(range, path.size() - 1); i++) {
            if (!map.getCell(path.get(i)).isPassable(movable)) {
                Location origin = path.get(0);
                Location destination = path.get(path.size()-1);
                BFS bfs = new BFS(map, movable);
                path = bfs.pathTo(destination);
                if (path == null) {
                    this.patrolPath = null;
                    this.initialPath = null;
                    return;
                }
                break;
            }
        }

        int min = Math.min(range, path.size() - 1);
        movable.move(this.map.getCell(path.get(min)), map);
        for (int i = 0; i < min; i++) {
            path.removeFirst();
        }

        if (!isInitialized) {
            this.initialPath = path;
        } else {
            this.patrolPath = path;
        }

        if (!isInitialized && this.initialPath.size() == 1) {
            isInitialized = true;
            BFS bfs = new BFS(this.map, movable);
            patrolPath = bfs.pathTo(destination2);
        }

        if (isInitialized && this.patrolPath.size() == 1) {
            Location otherDestination = (patrolPath.get(0) == destination2) ? destination1 : destination2;
            this.patrolPath = new BFS(this.map, movable).pathTo(otherDestination);
        }
    }

    public boolean isValid() {
        if (!isInitialized && this.initialPath == null) {
            return false;
        }
        if (isInitialized && this.patrolPath == null) {
            return false;
        }
        if (movable.getDestructable().getHitpoint() <= 0) {
            return false;
        }
        return true;
    }

    public Patrol(GameMap map, Movable movable, int x1, int y1, int x2, int y2) {
        this.movementSpeed = movable.getMovementSpeed();
        this.movable = movable;
        this.map = map;
        destination1 = map.getCell(x1, y1).getLocation();
        destination2 = map.getCell(x2, y2).getLocation();
        BFS bfs = new BFS(map, movable);
        initialPath = bfs.pathTo(destination1);
    }

    public Movable getOwner() {
        return movable;
    }
}
