package view;

import controller.view_controllers.MapNavigationMenuController;
import view.enums.MapNavigationMenuRegexes;

import java.util.Scanner;

public class MapNavigationMenu {
    private final MapNavigationMenuController controller;
    private final Scanner scanner;

    public MapNavigationMenu(MapNavigationMenuController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public String run() {
       controller.showMap();
       while (true) {
           String command = scanner.nextLine();

           if (MapNavigationMenuRegexes.MOVE_MAP.getMatcher(command).matches()) {
               controller.move(command);
               continue;
           }
           if (MapNavigationMenuRegexes.SHOW_DETAILS.getMatcher(command).matches()) {
               System.out.println(controller.showDetails(command));
               continue;
           }
           if (MapNavigationMenuRegexes.EXIT.getMatcher(command).matches()) {
               System.out.println("Exited MapNavigation Menu");
               return "Exit";
           }

           System.out.println("Invalid command.");
       }
    }
}