package view;

import controller.view_controllers.MapEditMenuController;
import view.enums.MapEditMenuRegexes;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapEditMenu {
    final MapEditMenuController controller;
    final Scanner scanner;

    public MapEditMenu(MapEditMenuController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public String run() {
        System.out.println("Entered MapEdit menu.");
        System.out.print("your options are:\n" +
                "1. clear -x <x> -y <y>\n" +
                "2. dropbuilding -x <x> -y <y> -t <type>\n" +
                "3. droptree -x <x> -y <y> -t <type>\n" +
                "4. dropunit -x <x> -y <y> -t <type> -c <count>\n" +
                "5. droprock -x <x> -y <y> -d <direction>\n" +
                "6. settexture -x <x> -y <y> -t <type>\n" +
                "7. settexture -x1 <x1> -y1 <y1> -x2 <x2> -y2 <y2> -t <type>\n" +
                "9. exit\n");
        Matcher matcher;
        while (true) {
            String input = scanner.nextLine();

            if ((matcher = MapEditMenuRegexes.CLEAR.getMatcher(input)).matches()) {
                System.out.println(controller.clear(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.DROP_BUILDING.getMatcher(input)).matches()) {
                System.out.println(controller.dropBuilding(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.DROP_TREE.getMatcher(input)).matches()) {
                System.out.println(controller.dropTree(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.DROP_UNIT.getMatcher(input)).matches()) {
                System.out.println(controller.dropUnit(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.DROP_ROCK.getMatcher(input)).matches()) {
                System.out.println(controller.dropRock(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.SET_TEXTURE.getMatcher(input)).matches() ||
                    (matcher = MapEditMenuRegexes.SET_TEXTURE2.getMatcher(input)).matches()) {
                System.out.println(controller.setTexture(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.EXIT.getMatcher(input)).matches())
                return "Exit";

            System.out.println("Invalid command!");
        }
    }
}