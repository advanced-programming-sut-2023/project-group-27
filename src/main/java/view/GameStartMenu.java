package view;

import controller.view_controllers.GameStartMenuController;
import view.enums.GameStartMenuRegexes;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameStartMenu {
    private final GameStartMenuController controller;
    private final Scanner scanner;

    public GameStartMenu(GameStartMenuController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public String run() {
        String command, result;
        Matcher matcher;
        System.out.println("Entered Game Start Menu\n" +
                "your options are:\n" +
                "1.show all users\n" +
                "2.show all maps\n" +
                "3.show selected players\n" +
                "4.show selected map info\n" +
                "5.navigate selected map\n" +
                "6.add player <number>\n" +
                "7.remove player <number>\n" +
                "8.select map <number>\n" +
                "9.show all colors\n" +
                "10.set color <number> <colorType>\n" +
                "11.reset colors\n" +
                "12.assign keeps and start <numbers>[]\n"+
                "13.Exit");

        while (true) {
            command = scanner.nextLine();

            if (GameStartMenuRegexes.EXIT.getMatcher(command).matches())
                return "Exit";
            if ((matcher = GameStartMenuRegexes.NAVIGATE_SELECTED_MAP.getMatcher(command)).matches()) {
                result = controller.navigateSelectedMap();
                if (result.equals("Enter Map Navigation Menu"))
                    return result;

                System.out.println(result);
                continue;
            }
            if ((matcher = GameStartMenuRegexes.ASSIGN_KEEPS_AND_START.getMatcher(command)).matches()) {
                result = controller.assignKeepsAndStart(matcher.group("data"));
                if (result.equals("Start Game"))
                    return result;

                System.out.println(result);
                continue;
            }
            if (GameStartMenuRegexes.SHOW_ALL_USERS.getMatcher(command).matches()) {
                System.out.println(controller.showAllUsers());
                continue;
            }
            if (GameStartMenuRegexes.SHOW_ALL_MAPS.getMatcher(command).matches()) {
                System.out.println(controller.showAllMaps());
                continue;
            }
            if (GameStartMenuRegexes.SHOW_GAME_PLAYERS.getMatcher(command).matches()) {
                System.out.println(controller.showGamePlayers());
                continue;
            }
            if (GameStartMenuRegexes.SHOW_SELECTED_MAP_INFO.getMatcher(command).matches()) {
                System.out.println(controller.showSelectedMapInfo());
                continue;
            }
            if ((matcher = GameStartMenuRegexes.ADD_PLAYER.getMatcher(command)).matches()) {
                System.out.println(controller.addPlayer(matcher.group("data")));
                continue;
            }
            if ((matcher = GameStartMenuRegexes.REMOVE_PLAYER.getMatcher(command)).matches()) {
                System.out.println(controller.removePlayer(matcher.group("data")));
                continue;
            }
            if ((matcher = GameStartMenuRegexes.SELECT_MAP.getMatcher(command)).matches()) {
                System.out.println(controller.selectMap(matcher.group("data")));
                continue;
            }
            if (GameStartMenuRegexes.SHOW_COLORS.getMatcher(command).matches()) {
                System.out.println(controller.showAllColors());
                continue;
            }
            if ((matcher = GameStartMenuRegexes.SET_COLOR.getMatcher(command)).matches()) {
                System.out.println(controller.setColor(matcher.group("data")));
                continue;
            }
            if (GameStartMenuRegexes.RESET_COLORS.getMatcher(command).matches()) {
                System.out.println(controller.resetColors());
                continue;
            }

            System.out.println("Invalid command!");
        }
    }
}