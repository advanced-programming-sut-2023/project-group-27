package view;

import controller.view_controllers.MapEditMenuController;
import view.enums.MapEditMenuRegexes;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapConfigurationMenu extends MapEditMenu {

    public MapConfigurationMenu(MapEditMenuController controller, Scanner scanner) {
        super(controller, scanner);
    }

    @Override
    public String run() {
        System.out.println("Entered MapConfiguration menu.");
        System.out.print("your options are:\n" +
                "1. clear -x <x> -y <y>\n" +
                "2. droptree -x <x> -y <y> -t <type>\n" +
                "3. droprock -x <x> -y <y> -d <direction>\n" +
                "4. settexture -x <x> -y <y> -t <type>\n" +
                "5. settexture -x1 <x1> -y1 <y1> -x2 <x2> -y2 <y2> -t <type>\n" +
                "6. exit\n");
        Matcher matcher;
        while (true) {
            String input = scanner.nextLine();

            if ((matcher = MapEditMenuRegexes.CLEAR.getMatcher(input)).matches()) {
                System.out.println(super.controller.clear(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.EXIT.getMatcher(input)).matches())
                return "Exit";
            if ((matcher = MapEditMenuRegexes.DROP_TREE.getMatcher(input)).matches()) {
                System.out.println(controller.dropTree(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.SET_TEXTURE.getMatcher(input)).matches() ||
                    (matcher = MapEditMenuRegexes.SET_TEXTURE2.getMatcher(input)).matches()) {
                System.out.println(controller.setTexture(matcher.group("data")));
                continue;
            }
            if ((matcher = MapEditMenuRegexes.DROP_ROCK.getMatcher(input)).matches()) {
                System.out.println(controller.dropRock(matcher.group("data")));
                continue;
            }
            System.out.println("Invalid command!");
        }
    }
}