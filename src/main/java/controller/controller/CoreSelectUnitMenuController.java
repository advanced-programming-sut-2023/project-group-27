package controller.controller;

import controller.view_controllers.SelectUnitMenuController;
import model.GameMap;
import model.Match;
import model.Movable;
import model.Selectable;
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
    private final GameMap map;
    private final ArrayList<Selectable> theSelected;
    private final SelectUnitMenu selectUnitMenu;
    private final SelectUnitMenuController selectUnitController;
    public CoreSelectUnitMenuController(ArrayList<Selectable> theSelected , Match match , Scanner scanner, GameMap map) {
        this.scanner = scanner;
        this.currentMatch = match;
        this.theSelected = theSelected;
        this.map = map;
        selectUnitController = new SelectUnitMenuController(theSelected, this, map, scanner);
        selectUnitMenu = selectUnitController.getUnitMenu();
    }

    public void run(){
        selectUnitMenu.run();
    }

    public void moveTo(int x,int y) {
        for (Selectable selectable : theSelected) {
            currentMatch.addTask(new Move(map , (Movable) selectable , x , y));
            //TODO maybe change movable to and array list of movables
        }
    }

    public void patrol(int x1, int y1, int x2, int y2) {
        for (Selectable selectable : theSelected) {
            currentMatch.addTask(new Patrol(map, (Movable) selectable, x1 , y1 , x2 , y2));
            //TODO maybe change movable to and array list of movables
        }
    }

    public String setStatus(String state) {
        for (Selectable selectable : theSelected) {
            if (!(selectable instanceof Soldier)) return "The selected unit must be a soldier!";
            ((Soldier) selectable).setState(state);
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
