package controller.controller;

import controller.view_controllers.SelectUnitMenuController;
import model.Match;
import model.man.Man;
import model.man.Soldier;
import model.task.Move;
import model.task.Patrol;
import view.SelectUnitMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class CoreSelectUnitMenuController {
    private final Scanner scanner;
    private final Match currentMatch;
    private final ArrayList<Man> selectedMen;
    private final SelectUnitMenu selectUnitMenu;
    private final SelectUnitMenuController selectUnitController;
    public CoreSelectUnitMenuController(ArrayList<Man> selectedMen , Match match , Scanner scanner) {
        this.scanner = scanner;
        this.currentMatch = match;
        this.selectedMen = selectedMen;
        selectUnitController = new SelectUnitMenuController(selectedMen, this, scanner);
        selectUnitMenu = selectUnitController.getUnitMenu();
    }

    public void run(){
        selectUnitMenu.run();
    }

    public void moveTo(int x,int y) {
        for (Man man : selectedMen) {
            currentMatch.addTask(new Move(x , y, man));
        }
    }

    public void patrol(int x1, int y1, int x2, int y2) {
        for (Man man : selectedMen) {
            currentMatch.addTask(new Patrol(x1 , y1 , x2 , y2, man));
        }
    }

    public String setStatus(String state) {
        for (Man man : selectedMen) {
            if (!(man instanceof Soldier)) return "The selected unit must be a soldier!";
            ((Soldier) man).setState(state);
        }
        return "State set successfully!";
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
