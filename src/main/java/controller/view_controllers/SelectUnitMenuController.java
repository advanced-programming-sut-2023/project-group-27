package controller.view_controllers;

import controller.controller.CoreSelectUnitMenuController;
import model.man.Man;
import view.SelectUnitMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SelectUnitMenuController {
    private final CoreSelectUnitMenuController coreController;
    private final SelectUnitMenu unitMenu;

    public SelectUnitMenuController(Man selectedMan, CoreSelectUnitMenuController coreController, Scanner scanner) {
        this.coreController = coreController;
        this.unitMenu = new SelectUnitMenu(this, scanner);
    }

    public SelectUnitMenu getUnitMenu() {
        return unitMenu;
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
