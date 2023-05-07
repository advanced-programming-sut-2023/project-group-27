package controller.controller;

import controller.view_controllers.SelectUnitMenuController;
import model.*;
import model.man.Soldier;
import model.man.SoldierType;
import model.task.AirStrike;
import model.task.Fight;
import model.task.Move;
import model.task.Patrol;
import view.SelectUnitMenu;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CoreSelectUnitMenuController {
    private final Scanner scanner;
    private final Match currentMatch;
    private final GameMap map;
    private final ArrayList<Selectable> theSelected;
    private final SelectUnitMenu selectUnitMenu;
    private final SelectUnitMenuController selectUnitController;
    private final SoldierType type;
    public CoreSelectUnitMenuController(ArrayList<Selectable> theSelected , Match match , Scanner scanner, GameMap map, SoldierType type) {
        this.scanner = scanner;
        this.currentMatch = match;
        this.theSelected = theSelected;
        this.map = map;
        this.type = type;
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
        ArrayList<Selectable> selectableEnemies = map.getCell(x , y).getSelectables();
        for (Selectable selectable : selectableEnemies) {
            if (selectable instanceof Fightable && !((Soldier) selectable).isFighting()) {
                for (Selectable enemy : selectableEnemies) {
                    if (enemy instanceof Fightable && !((Soldier) enemy).isFighting())
                        currentMatch.addTask(new Fight((Fightable) selectable, (Destructable) enemy));
                }
            }
        }
        return null;
    }

    public String attackByXY(int x, int y) {
        if (type.range == null) return "Selected unit is not ranged!";
        int xUnit = theSelected.get(0).getLocation().x;
        int yUnit = theSelected.get(0).getLocation().y;
        int distance = Math.abs(x - xUnit) + Math.abs(y - yUnit);
        if (type.range < distance) return "Target is out of range!";
        ArrayList<Selectable> selectableEnemies = map.getCell(x , y).getSelectables();
        Random random = new Random();
        for (Selectable selectable : theSelected) {
            currentMatch.addTask(new AirStrike((Fightable) selectable ,
                    (Destructable) selectableEnemies.get(random.nextInt() % selectableEnemies.size())));
        }
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
