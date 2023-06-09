package model;

public class GameMap {
    private final String mapName;
    private final int width;
    private final int height;

    private final int capacity;
    private final Cell [][] map;
    private final Location[] keepsLocations;

    public GameMap(int width, int height, String name, int capacity, Location[] keepsLocations) {
        this.width = width;
        this.height = height;
        this.capacity = capacity;
        this.keepsLocations = keepsLocations;
        map = new Cell[width][height];
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                map[i][j] = new Cell(LandType.PLAIN, i, j);
        this.mapName = name;
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

    public int getCapacity() {
        return capacity;
    }

    public Cell getCell(int x, int y) {
        return map[x][y];
    }

    public Cell getCell(Location location) {
        return getCell(location.x, location.y);
    }

    public Cell[][] getMap() {
        return map;
    }

    public Cell[] getKeepsLocations() {
        Cell[] cells = new Cell[keepsLocations.length];
        for (int i = 0; i < keepsLocations.length; i++)
            cells[i] = getCell(keepsLocations[i]);
        return cells;
    }
}