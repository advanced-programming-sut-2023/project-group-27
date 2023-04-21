package controller.controller;

import model.Location;

public class CoreMapNavigationMenuController {
    private Location location;

    public CoreMapNavigationMenuController(int x, int y) {
        this.location = new Location(x, y);
    }

    public void move(int deltaX, int deltaY) {
        location.x += deltaX;
        location.y += deltaY;
    }

    public String showMap() {
        return null;
    }
}