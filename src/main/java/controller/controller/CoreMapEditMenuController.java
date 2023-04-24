package controller.controller;

import controller.view_controllers.MapEditMenuController;
import view.MapEditMenu;

import java.util.Scanner;

public class CoreMapEditMenuController {
    private final Scanner scanner;
    private final MapEditMenu mapEditMenu;

    public CoreMapEditMenuController(Scanner scanner) {
        this.scanner = scanner;
        mapEditMenu = new MapEditMenu(new MapEditMenuController(this));
    }

    public void run(){
        mapEditMenu.run(scanner);
    }
}
