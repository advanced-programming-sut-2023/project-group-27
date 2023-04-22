package controller.controller;

import controller.view_controllers.MapEditMenuController;
import view.MapEditMenu;

import java.util.Scanner;

public class CoreMapEditMenuController {
    private final MapEditMenu mapEditMenu;

    public CoreMapEditMenuController() {
        mapEditMenu = new MapEditMenu(new MapEditMenuController());
    }

    public void run(Scanner scanner){
        mapEditMenu.run(scanner);
    }
}
