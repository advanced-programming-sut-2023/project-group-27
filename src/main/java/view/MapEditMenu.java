package view;

import controller.view_controllers.MapEditMenuController;
import view.enums.MapEditMenuRegexes;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapEditMenu {
    private final MapEditMenuController controller;
    private final Scanner scanner;

    public MapEditMenu(MapEditMenuController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public String run() {
        System.out.println("Entered MapEdit menu.");
        Matcher matcher;
        while (true) {
            String input = scanner.nextLine();

            if ((matcher = MapEditMenuRegexes.CLEAR.getMatcher(input)).matches()) {
                System.out.println(controller.clear(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.DROPBUILDING.getMatcher(input)).matches()) {
                System.out.println(controller.dropBuilding(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.DROPTREE.getMatcher(input)).matches()) {
                System.out.println(controller.dropTree(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.DROPUNIT.getMatcher(input)).matches()) {
                System.out.println(controller.dropUnit(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.DROPROCK.getMatcher(input)).matches()) {
                System.out.println(controller.dropRock(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.SETTEXTURE.getMatcher(input)).matches() ||
                    (matcher = MapEditMenuRegexes.SETTEXTURE2.getMatcher(input)).matches()) {
                System.out.println(controller.settexture(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.EXIT.getMatcher(input)).matches())
                return "Exit";

            System.out.println("Invalid command!");
        }
    }
}