package view;

import controller.view_controllers.MapNavigationMenuController;

import java.util.Scanner;

public class MapNavigationMenu {
    private MapNavigationMenuController controller;
    private final Scanner scanner;

    public MapNavigationMenu(MapNavigationMenuController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public String run() {
       controller.showMap();
       String command = scanner.nextLine();
       //TODO complete run
        return null;
    }
}
