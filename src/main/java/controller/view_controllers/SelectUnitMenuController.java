package controller.view_controllers;

import controller.controller.CoreSelectUnitMenuController;
import model.man.Man;

import java.util.regex.Matcher;

public class SelectUnitMenuController {
    private final CoreSelectUnitMenuController coreController;

    public SelectUnitMenuController(Man selectedMan, CoreSelectUnitMenuController coreController) {
        this.coreController = coreController;
    }

    public String moveTo(Matcher matcher) {
        return null;
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
