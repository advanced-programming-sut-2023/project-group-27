package model;

public class GameMap {
    private String mapName;
    private final int size;
    private final Cell [][] map;
    public GameMap(int size) {
        this.size = size;
        map = new Cell[size][size];
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
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
