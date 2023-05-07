package model;

public class GameMap {
    private String mapName;
    private final int width;
    private final int height;
    private final Cell [][] map;

    public GameMap(int width, int height, String name) {
        this.width = width;
        this.height = height;
        map = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = new Cell(LandType.PLAIN, i, j);
            }
        }
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

    public Cell getCell(int x, int y) {
        return map[x][y];
    }

    public Cell getCell(Location location) {
        return getCell(location.x, location.y);
    }

    public Cell[][] getMap() {
        return map;
    }
}
