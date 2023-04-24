package controller.controller;

import controller.view_controllers.SelectUnitMenuController;
import model.man.Man;
import view.SelectUnitMenu;

import java.util.Scanner;

public class CoreSelectUnitMenuController {
    private final Scanner scanner;
    private Man selectedMan;
    private final SelectUnitMenu selectUnitMenu;
    public CoreSelectUnitMenuController(Man selectedMan , Scanner scanner) {
        this.scanner = scanner;
        this.selectedMan = selectedMan;
        selectUnitMenu = new SelectUnitMenu(new SelectUnitMenuController(selectedMan));
    }

    public void run(){
        selectUnitMenu.run(scanner);
    }

    public String moveTo(int x,int y) {
        return null;
    }

    public String patrol(int x1, int y1, int x2, int y2) {
        return null;
    }

    public String setStatus(String state) {
        return null;
    }

    public String attackByEnemy(int x, int y) {
        return null;
    }

    public String attackByXY(int x, int y) {
        return null;
    }

    public String pourOil(String direction) {
        return null;
    }

    public String digTunnel(int x, int y) {
        return null;
    }

    public String build(String equipmentName) {
        return null;
    }

    public void disbandUnit() {

    }
}
