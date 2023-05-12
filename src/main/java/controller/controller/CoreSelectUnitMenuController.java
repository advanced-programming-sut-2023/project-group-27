package controller.controller;

import controller.view_controllers.SelectUnitMenuController;
import model.*;
import model.building.Building;
import model.man.Man;
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
    private User currentUser;
    private final GameMap map;
    private final ArrayList<Selectable> theSelected;
    private final SelectUnitMenu selectUnitMenu;
    private final SelectUnitMenuController selectUnitController;
    private final SoldierType type;
    public CoreSelectUnitMenuController(ArrayList<Selectable> theSelected , Match match , Scanner scanner, User currentUser, GameMap map, SoldierType type) {
        this.scanner = scanner;
        this.currentMatch = match;
        this.theSelected = theSelected;
        this.currentUser = currentUser;
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
        ArrayList<Selectable> selectableEnemies = map.getCell(x , y).getSelectables();
        for (Selectable selectable : selectableEnemies) {
            if (selectable instanceof Man) {
                if (((Man) selectable).getOwner().equals(currentUser))
                    selectableEnemies.remove(selectable);
            }
            if (selectable instanceof Building) {
                if (((Building) selectable).getOwner().equals(currentUser))
                    selectableEnemies.remove(selectable);
            }
        }
        Random random = new Random();
        for (Selectable selectable : theSelected) {
            currentMatch.addTask(new AirStrike((Fightable) selectable , type ,
                    (Destructable) selectableEnemies.get(random.nextInt() % selectableEnemies.size()) , x , y));
        }
        return null;
    }

    public String digTunnel(int x, int y) {
        return null;
    }

    public String build(String equipmentName) {
        if (!type.getName().equals("Engineer")) return "The selected unit must be engineer!";
        int engineerCount = theSelected.size();
        switch (equipmentName) {
            case "shield" :
                if (engineerCount < 1) return "You need at least 1 engineer!";
                break;
            case "battering ram" :
                if (engineerCount < 4) return "You need at least 4 engineers!";
                break;
            case "siege tower" :
                if (engineerCount < 4) return "You need at least 4 engineers!";
                break;
            case "catapult" :
                if (engineerCount < 2) return "You need at least 2 engineers!";
                break;
            case "fixed catapult" :
                if (engineerCount < 3) return "You need at least 3 engineers!";
                break;
            default:
                return "Invalid equipment name!";
        }
        return null;
    }

    public void disbandUnit() {
        int size = theSelected.size();
        theSelected.clear();
        currentUser.getMonarchy().changePopularity(size);
    }
}
