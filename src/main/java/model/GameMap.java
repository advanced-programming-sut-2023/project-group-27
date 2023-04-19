package model;

public class GameMap {
    private String mapName;
    private final int width;
    private final int height;
    private final Cell [][] map;
    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        map = new Cell[width][height];
    }

    public String getMapName() {
        return mapName;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getCell(int x, int y) {
        return map[x][y];
    }

    public Cell[][] getMap() {
        return map;
    }

    public Cell[][] showMap(int x , int y , int sizeToBeShown)
    {
        return null;
    }
}
