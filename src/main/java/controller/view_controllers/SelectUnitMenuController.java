package controller.view_controllers;

import controller.controller.CoreSelectUnitMenuController;
import model.man.Man;

import java.util.Map;
import java.util.regex.Matcher;

public class SelectUnitMenuController {
    private final CoreSelectUnitMenuController coreController;
    private final Man selectedMan;

    public SelectUnitMenuController(Man selectedMan, CoreSelectUnitMenuController coreController) {
        this.coreController = coreController;
        this.selectedMan = selectedMan;
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
        if (x <= 0 || y <= 0) return "Invalid coordinate amount";
        coreController.moveTo(x , y);
        return "Move Success!";
    }

    public String patrol(Matcher matcher) {
        return null;
    }

    public String setStatus(Matcher matcher) {
        return null;
    }

    public String attackByEnemy(Matcher matcher) {
        return null;
    }

    public String attackByXY(Matcher matcher) {
        return null;
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
