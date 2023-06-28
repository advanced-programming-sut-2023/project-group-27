package graphics_view.graphical_controller;

import controller.controller.CoreGameMenuController;
import controller.controller.CoreMapEditMenuController;
import controller.controller.CoreSelectUnitMenuController;
import model.Cell;
import model.GameMap;
import model.Match;
import model.Selectable;
import model.man.Man;
import model.man.SoldierType;
import server.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandListener extends Thread{
    private Connection connection;
    private Match match;

    public CommandListener(Connection connection, Match match) {
        this.match = match;
        this.connection = connection;
    }

    @Override
    public void run() {
        while (true) {
            String command = null;
            try {
                command = connection.receive();
                if (command.startsWith("game")) {
                    new CoreGameMenuController(match, new Scanner(command.substring(5) + "\nExit")).run();
                } else if (command.startsWith("selectunit")) {
                    command = command.substring(11);
                    Pattern pattern = Pattern.compile("-x (?<x>\\d+) -y (?<y>\\d+) -h (?<h>\\d+)");
                    Matcher matcher = pattern.matcher(command);
                    if (matcher.find()) {
                        int x = Integer.parseInt(matcher.group("x"));
                        int y = Integer.parseInt(matcher.group("y"));
                        int hash = Integer.parseInt(matcher.group("h"));

                        GameMap map = match.getCurrentMatchMap();
                        Cell cell = map.getCell(x, y);
                        Selectable selectable = null;
                        for (Man man: cell.getMen()) {
                            if (man.hashCode() == hash) {
                                selectable = man;
                            }
                        }
                        String command2 = command.substring(matcher.end() + 1);
                        CoreSelectUnitMenuController controller = new CoreSelectUnitMenuController(
                                new ArrayList<>(List.of(selectable)), match, new Scanner(command2 + "\nExit"),
                                match.getCurrentUser(), map, SoldierType.getTypeByName(selectable.getName()));
                        controller.run();
                    }
                } else if (command.startsWith("mapEdit")) {
                    command = command.substring(8);
                    CoreMapEditMenuController controller =
                            new CoreMapEditMenuController(match, new Scanner(command + "\nExit"));
                    controller.run();
                }
                System.out.println(command);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
