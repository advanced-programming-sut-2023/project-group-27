package view;

import controller.view_controllers.SelectBuildingMenuController;
import view.enums.SelectBuildingMenuRegexes;

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