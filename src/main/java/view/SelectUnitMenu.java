package view;

import controller.view_controllers.SelectUnitMenuController;
import view.enums.SelectUnitMenuRegexes;

import java.util.Scanner;

public class SelectUnitMenu extends MenuBase{
    private final SelectUnitMenuController controller;

    public SelectUnitMenu(SelectUnitMenuController controller , Scanner scanner) {
        super(scanner);
        this.controller = controller;
    }

    public String run() {
        String command , output;
        while (true) {
            command = scanner.nextLine();
            if (SelectUnitMenuRegexes.EXIT.getMatcher(command).matches()) return "Exit";
            else if (SelectUnitMenuRegexes.MOVETO.getMatcher(command).matches()) {
                output = controller.moveTo(SelectUnitMenuRegexes.MOVETO.getMatcher(command));
                System.out.println(output);
            }
            else if (SelectUnitMenuRegexes.PATROLUNIT.getMatcher(command).matches()) {
                output = controller.patrol(SelectUnitMenuRegexes.PATROLUNIT.getMatcher(command));
                System.out.println(output);
            }
            else if (SelectUnitMenuRegexes.SETSTATUS.getMatcher(command).matches()) {
                output = controller.setStatus(SelectUnitMenuRegexes.SETSTATUS.getMatcher(command));
                System.out.println(output);
            }
            else if (SelectUnitMenuRegexes.ATTACK.getMatcher(command).matches()) {
                output = controller.attack(SelectUnitMenuRegexes.ATTACK.getMatcher(command));
                System.out.println(output);
            }
            else if (SelectUnitMenuRegexes.POUROIL.getMatcher(command).matches()) {
                output = controller.pourOil(SelectUnitMenuRegexes.POUROIL.getMatcher(command));
                System.out.println(output);
            }
            else if (SelectUnitMenuRegexes.DIGTUNNEL.getMatcher(command).matches()) {
                output = controller.digTunnel(SelectUnitMenuRegexes.DIGTUNNEL.getMatcher(command));
                System.out.println(output);
            }
            else if (SelectUnitMenuRegexes.BUILD.getMatcher(command).matches()) {
                output = controller.build(SelectUnitMenuRegexes.BUILD.getMatcher(command));
                System.out.println(output);
            }
            else if (SelectUnitMenuRegexes.DISBAND.getMatcher(command).matches()) {
                controller.disbandUnit();
            }
            else System.out.println("Invalid command!");
        }
    }
}
