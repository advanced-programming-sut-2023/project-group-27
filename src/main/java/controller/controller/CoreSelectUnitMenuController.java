package controller.controller;

import controller.view_controllers.SelectUnitMenuController;
import model.man.Man;
import model.man.Soldier;
import model.task.Move;
import model.task.Patrol;
import model.task.Task;
import view.SelectUnitMenu;

import java.util.Scanner;

public class CoreSelectUnitMenuController {
    private final Scanner scanner;
    private Man selectedMan;
    private final SelectUnitMenu selectUnitMenu;
    private final SelectUnitMenuController selectUnitController;
    public CoreSelectUnitMenuController(Man selectedMan , Scanner scanner) {
        this.scanner = scanner;
        this.selectedMan = selectedMan;
        selectUnitController = new SelectUnitMenuController(selectedMan, this, scanner);
        selectUnitMenu = selectUnitController.getUnitMenu();
    }

    public void run(){
        selectUnitMenu.run();
    }

    public void moveTo(int x,int y) {
        Task task = new Move(x , y);
        //TODO add task to task to do list

    }

    public void patrol(int x1, int y1, int x2, int y2) {
        Task task = new Patrol(x1 , y1 , x2 , y2);
        //TODO add task to task to do list
    }

    public String setStatus(String state) {
        if (!(selectedMan instanceof Soldier)) return "The selected unit must be a soldier!";
        ((Soldier) selectedMan).setState(state);
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
