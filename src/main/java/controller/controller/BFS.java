package controller.controller;

import model.GameMap;
import model.Location;
import model.Movable;
import model.StrongholdCrusader;

import java.util.*;

public class BFS {
    Location startingLocation;
    GameMap map;
    Map<Location, Location> parent = new HashMap<>();

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

    public LinkedList<Location> pathTo(Location destination) {
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
}
