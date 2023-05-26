package console_view;

import controller.view_controllers.SelectBuildingMenuController;
import console_view.enums.SelectBuildingMenuRegexes;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SelectBuildingMenu {
    private final SelectBuildingMenuController controller;
    private final Scanner scanner;

    public SelectBuildingMenu(SelectBuildingMenuController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public String run() {
        System.out.print("Entered Building Selection Menu.\n");
        System.out.print("your options are:\n" +
                "1. repair\n" +
                "2. create unit -t [type] -c [count]\n" +
                "3. Exit\n");
        String command = scanner.nextLine();
        Matcher matcher;
        if (SelectBuildingMenuRegexes.EXIT.getMatcher(command).matches()) {
            System.out.println("Exited Building Selection Menu.\n");
            return "Exit";
        }
        if (SelectBuildingMenuRegexes.REPAIR.getMatcher(command).matches())
            System.out.println(controller.repair());
        else if ((matcher = SelectBuildingMenuRegexes.CREATEUNIT.getMatcher(command)).matches()) {
            System.out.println(controller.createUnit(matcher));
        }
        else System.out.println("Invalid command!\n");
        return "";
    }
}