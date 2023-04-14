package model;

public class Map {
    private final int size;
    private final Cell [][] map;
    public Map(int size) {
        this.size = size;
        map = new Cell[size][size];
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getMap() {
        return map;
    }

    public Cell[][] showMap(int x , int y , int sizeToBeShown)
    {
        return null;
    }
}
