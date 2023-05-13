package controller.controller;

import model.GameMap;
import model.Location;
import model.Movable;
import model.StrongholdCrusader;
import model.man.Man;
import model.man.Soldier;

import java.util.*;

public class BFS {
    private Location startingLocation;
    private GameMap map;
    private final Map<Location, Location> parent = new HashMap<>();
    private final Map<Location , Integer> height = new HashMap<>();
    private Soldier target;

    public BFS(GameMap map, Movable movable) {
        this.map = map;
        Queue<Location> cells = new LinkedList<>();
        cells.add(movable.getLocation());
        startingLocation = cells.peek();
        parent.put(startingLocation, null);
        while (!cells.isEmpty()) {
            Location currentLocation = cells.poll();
            List<Location> locationNeighbors = currentLocation.getNeighbors(map, movable);
            for (Location neighbor : locationNeighbors) {
                if (!parent.containsKey(neighbor)) {
                    parent.put(neighbor, currentLocation);
                    cells.add(neighbor);
                }
            }
        }
    }

    public BFS(GameMap map, Movable movable , int maxDepth) {
        this.target = null;
        this.map = map;
        Queue<Location> cells = new LinkedList<>();
        cells.add(movable.getLocation());
        startingLocation = cells.peek();
        height.put(startingLocation , 0);
        parent.put(startingLocation, null);
        while (!cells.isEmpty()) {
            Location currentLocation = cells.poll();
            List<Man> enemies = map.getCell(currentLocation).getMen().stream().filter(
                    x -> (x.getOwner() != movable.getOwner() && x instanceof Soldier)).toList();
            if (enemies.size() > 0) {
                Random random = new Random();
                if (target == null) {
                    this.target = (Soldier) enemies.get(random.nextInt() % enemies.size());
                }
            }
            if (height.get(currentLocation) == maxDepth) {
                continue;
            }
            List<Location> locationNeighbors = currentLocation.getNeighbors(map, movable);
            for (Location neighbor : locationNeighbors) {
                if (!parent.containsKey(neighbor)) {
                    parent.put(neighbor, currentLocation);
                    cells.add(neighbor);
                    height.put(neighbor , height.get(currentLocation) + 1);
                }
            }
        }
    }

    public LinkedList<Location> pathTo(Location destination) {
        destination = map.getCell(destination).getLocation();
        if (parent.get(destination) == null) {
            return null;
        }
        LinkedList<Location> result = new LinkedList<>();
        while (destination != null) {
            result.add(destination);
            destination = parent.get(destination);
        }
        Collections.reverse(result);
        return result;
    }

    public Soldier getTarget() {
        return target;
    }
}
