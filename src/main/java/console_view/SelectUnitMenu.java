package console_view;

import controller.view_controllers.SelectUnitMenuController;
import console_view.enums.SelectUnitMenuRegexes;

import java.util.Scanner;

public class SelectUnitMenu extends MenuBase{
    private final SelectUnitMenuController controller;

    public SelectUnitMenu(SelectUnitMenuController controller , Scanner scanner) {
        super(scanner);
        this.controller = controller;
    }

    public String run() {
        System.out.print("Entered Select Unit Menu\n");
        System.out.println("your options are:\n" +
                "1. move unit to -x [x] -y [y]\n" +
                "2. patrol unit -x1 [x] -y1 [y] -x2 [x] -y2 [y]\n" +
                "3. set -x [x] -y [y] -s [standing|defensive|offensive]\n" +
                "4. attack -e [enemy’s x] [enemy’s y]\n" +
                "5. attack -x [x] -y [y]\n" +
                "6. pour oil -d [direction]\n" +
                "7. dig tunnel -x [x] -y [y]\n" +
                "8. build -q [equipment name]\n" +
                "9. disband unit\n" +
                "10. Exit\n");
        String command , output;
        while (true) {
            command = scanner.nextLine();
            if (SelectUnitMenuRegexes.EXIT.getMatcher(command).matches()) return "Exit";
            else if (SelectUnitMenuRegexes.MOVETO.getMatcher(command).matches()) {
                output = controller.moveTo(SelectUnitMenuRegexes.MOVETO.getMatcher(command));
                System.out.println(output);
            }
            else if (SelectUnitMenuRegexes.PATROL_UNIT.getMatcher(command).matches()) {
                output = controller.patrol(SelectUnitMenuRegexes.PATROL_UNIT.getMatcher(command));
                System.out.println(output);
            }
            else if (SelectUnitMenuRegexes.SET_STATUS.getMatcher(command).matches()) {
                output = controller.setStatus(SelectUnitMenuRegexes.SET_STATUS.getMatcher(command));
                System.out.println(output);
            }
            else if (SelectUnitMenuRegexes.ATTACK.getMatcher(command).matches()) {
                output = controller.attack(SelectUnitMenuRegexes.ATTACK.getMatcher(command));
                System.out.println(output);
            }
            else if (SelectUnitMenuRegexes.POUR_OIL.getMatcher(command).matches()) {
                output = controller.pourOil(SelectUnitMenuRegexes.POUR_OIL.getMatcher(command));
                System.out.println(output);
            }
            else if (SelectUnitMenuRegexes.DIG_TUNNEL.getMatcher(command).matches()) {
                output = controller.digTunnel(SelectUnitMenuRegexes.DIG_TUNNEL.getMatcher(command));
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
