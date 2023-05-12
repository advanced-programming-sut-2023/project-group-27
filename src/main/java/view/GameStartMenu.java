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
        while (true) {
            command = scanner.nextLine();

            if (GameStartMenuRegexes.EXIT.getMatcher(command).matches())
                return "Exit";
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

        }
    }
}