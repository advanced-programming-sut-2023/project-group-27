package controller.view_controllers;

import controller.controller.CoreMapNavigationMenuController;

public class NavigationMenuController {
    private CoreMapNavigationMenuController controller;
    public NavigationMenuController(int x, int y) {
        controller = new CoreMapNavigationMenuController(x, y);
    }

    public void move(int deltaX, int deltaY) {
    }

    public String showMap() {
        return null;
    }
}
