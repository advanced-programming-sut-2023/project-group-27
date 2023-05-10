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
       System.out.print("Entered MapNavigation Menu\n");
       System.out.print("your options are:\n" +
               "1. map <up|down|left|right>\n" +
               "2. show details -x <x> -y <y>\n" +
               "3. Exit\n");
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