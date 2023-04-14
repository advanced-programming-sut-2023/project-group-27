package controller.controller;

import model.Pair;
import model.StrongholdCrusader;

public class CoreMapNavigationMenuController {
    private Pair<Integer, Integer> location;

    public CoreMapNavigationMenuController(int x, int y) {
        this.location = new Pair<>(x, y);
    }

    public void move(int deltaX, int deltaY) {
        location.x += deltaX;
        location.y += deltaY;
    }

    public String showMap() {
        return null;
    }
}