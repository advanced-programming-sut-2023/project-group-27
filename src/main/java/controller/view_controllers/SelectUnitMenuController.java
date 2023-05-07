package controller.view_controllers;

import controller.controller.CoreSelectUnitMenuController;
import model.Fightable;
import model.GameMap;
import model.Movable;
import model.Selectable;
import model.man.Soldier;
import view.SelectUnitMenu;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SelectUnitMenuController {
    private final CoreSelectUnitMenuController coreController;
    private final ArrayList<Selectable> theSelected;
    private final GameMap map;
    private final SelectUnitMenu unitMenu;

    public SelectUnitMenuController(ArrayList<Selectable> theSelected, CoreSelectUnitMenuController coreController, GameMap map, Scanner scanner) {
        this.coreController = coreController;
        this.theSelected = theSelected;
        this.map = map;
        this.unitMenu = new SelectUnitMenu(this, scanner);
    }

    public SelectUnitMenu getUnitMenu() {
        return unitMenu;
    }

    public String moveTo(Matcher matcher) {
        matcher.matches();
        String argsString = matcher.group("options");
        Map <String , String> args = Utilities.extractOptionsFromString(argsString);
        if (args == null) return "Dont determine a field twice!";
        if (!args.containsKey("x")) return "Determine X coordinate!";
        if (!args.containsKey("y")) return "Determine Y coordinate!";
        int x , y;
        try {
            x = Integer.parseInt(args.get("x"));
            y = Integer.parseInt(args.get("y"));
        } catch (NumberFormatException e) {
            return "Invalid coordinate format";
        }
        if (Utilities.XYCheck(x, y , map) != null) return Utilities.XYCheck(x, y , map);
        if (!(theSelected.get(0) instanceof Movable))
            return "You can not move this unit!";
        coreController.moveTo(x , y);
        return "Move success!";
    }

    public String patrol(Matcher matcher) {
        matcher.matches();
        String argsString = matcher.group("options");
        Map <String , String> args = Utilities.extractOptionsFromString(argsString);
        if (args == null) return "Dont determine a field twice!";
        if (!args.containsKey("x1")) return "Determine X1 coordinate!";
        if (!args.containsKey("y1")) return "Determine Y1 coordinate!";
        if (!args.containsKey("x2")) return "Determine X2 coordinate!";
        if (!args.containsKey("y2")) return "Determine Y2 coordinate!";
        int x1 , y1 , x2 , y2;
        try {
            x1 = Integer.parseInt(args.get("x1"));
            y1 = Integer.parseInt(args.get("y1"));
            x2 = Integer.parseInt(args.get("x2"));
            y2 = Integer.parseInt(args.get("y2"));
        } catch (NumberFormatException e) {
            return "Invalid coordinate format!";
        }
        if (Utilities.XYCheck(x1, y1 , map) != null) return Utilities.XYCheck(x1, y1 , map);
        if (Utilities.XYCheck(x2, y2 , map) != null) return Utilities.XYCheck(x2, y2 , map);
        if (!(theSelected.get(0) instanceof Movable))
            return "You can not move this unit!";
        coreController.patrol(x1 , y1 , x2 , y2);
        return "Patrol success!";
    }

    public String setStatus(Matcher matcher) {
        matcher.matches();
        String argsString = matcher.group("options");
        Map <String , String> args = Utilities.extractOptionsFromString(argsString);
        if (args == null) return "Dont determine a field twice!";
        if (!args.containsKey("x")) return "Determine X coordinate!";
        if (!args.containsKey("y")) return "Determine Y coordinate!";
        int x , y;
        try {
            x = Integer.parseInt(args.get("x"));
            y = Integer.parseInt(args.get("y"));
        } catch (NumberFormatException e) {
            return "Invalid coordinate format";
        }
        if (Utilities.XYCheck(x, y , map) != null) return Utilities.XYCheck(x, y , map);
        String state = args.get("s");
        if (!state.matches("standing|defensive|offensive")) return "Invalid status format!";
        if (!(theSelected.get(0) instanceof Soldier))
            return "The selected unit type must be soldier!";
        return coreController.setStatus(state);
    }


    public String attack(Matcher matcher) {
        matcher.matches();
        String argsString = matcher.group("options");
        Map <String , String> args = Utilities.extractOptionsFromString(argsString);
        if (args == null) return "Dont determine a field twice!";
        if (!args.containsKey("x")) return "Determine X coordinate!";
        if (!args.containsKey("y")) return "Determine Y coordinate!";
        int x , y;
        try {
            x = Integer.parseInt(args.get("x"));
            y = Integer.parseInt(args.get("y"));
        } catch (NumberFormatException e) {
            return "Invalid coordinate format";
        }
        if (Utilities.XYCheck(x, y , map) != null) return Utilities.XYCheck(x, y , map);
        if (!(theSelected.get(0) instanceof Fightable))
            return "The selected unit can not fight!";
        if (args.containsKey("e")) return coreController.attackByEnemy(x , y);
        else return coreController.attackByXY(x , y);
    }

    public String pourOil(Matcher matcher) {
        return null;
    }

    public String digTunnel(Matcher matcher) {
        return null;
    }

    public String build(Matcher matcher) {
        return null;
    }

    public void disbandUnit() {

    }
}
