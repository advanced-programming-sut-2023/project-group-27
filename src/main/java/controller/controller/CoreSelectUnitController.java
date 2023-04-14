package controller.controller;

import model.man.Man;

public class CoreSelectUnitController {
    private Man selectedMan;

    public CoreSelectUnitController(Man selectedMan) {
        this.selectedMan = selectedMan;
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
