package model.task;

import controller.controller.BFS;
import model.Cell;
import model.GameMap;
import model.Location;
import model.Movable;

import java.util.List;
import java.util.Map;

public class Move implements Task {
    private BFS bfs;
    private GameMap map;
    private List<Location> path;
    private Double reminder;
    private Double movementSpeed;
    private final Movable movable;

    @Override
    public void run() {
        int range = (int) Math.floor(movementSpeed+reminder);
        reminder = movementSpeed + reminder - range;
        for (int i = 1; i <= range; i++) {
            if (!map.getCell(path.get(i)).isPassable(movable)) {
                Location origin = path.get(0);
                Location destination = path.get(path.size()-1);
                this.bfs = new BFS(origin, map);
                this.path = this.bfs.pathTo(destination);
                if (this.path == null) {
                    return;
                }
                break;
            }
        }
        this.movable.move(map.getCell(path.get(range)));
    }

    public Boolean isValid() {
        if (this.path == null) {
            return false;
        }
        return true;
    }

     public Move(GameMap map, Movable movable, int originX, int originY, int destinationX, int destinationY) {
        Location origin = new Location(originX, originY);
        Location destination = new Location(destinationX, destinationY);
        this.map = map;
        this.bfs = new BFS(origin, map);
        this.path = this.bfs.pathTo(destination);
        this.movable = movable;
    }
}
