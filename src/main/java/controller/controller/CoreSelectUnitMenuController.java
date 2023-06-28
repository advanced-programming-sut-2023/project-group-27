package controller.controller;

import controller.view_controllers.SelectUnitMenuController;
import model.*;
import model.building.Building;
import model.building.EngineerBuilding;
import model.building.EngineerBuildingType;
import model.man.Man;
import model.man.Soldier;
import model.man.SoldierType;
import model.task.AirStrike;
import model.task.Fight;
import model.task.Move;
import model.task.Patrol;
import console_view.SelectUnitMenu;

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
    private int x;
    private int y;
    private final SoldierType type;
    public CoreSelectUnitMenuController(ArrayList<Selectable> theSelected , Match match , Scanner scanner, User currentUser, GameMap map, int x, int y, SoldierType type) {
        this.scanner = scanner;
        this.currentMatch = match;
        this.theSelected = theSelected;
        this.currentUser = currentUser;
        this.map = map;
        this.x = x;
        this.y = y;
        this.type = type;
        selectUnitController = new SelectUnitMenuController(theSelected, this, map, scanner);
        selectUnitMenu = selectUnitController.getUnitMenu();
    }

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

    public ArrayList<Selectable> getTheSelected() {
        return theSelected;
    }


    public void run(){
        selectUnitMenu.run();
    }

    public void moveTo(int x,int y) {
        for (Selectable selectable : theSelected) {
            Move move = new Move(map, (Movable) selectable, x, y);
            currentMatch.addTask(move);
            if (selectable instanceof Man) {
                ((Man) selectable).setTask(move);
            }
        }
    }

    public String patrol(int x1, int y1, int x2, int y2) {
        if (theSelected.stream().anyMatch(selectable1 -> !(selectable1 instanceof Movable))) {
            return "The selected unit must be a movable!";
        }
        for (Selectable selectable : theSelected) {
            Patrol patrol = new Patrol(map, (Movable) selectable, x1 , y1 , x2 , y2);
            currentMatch.addTask(patrol);
            if (selectable instanceof Man) {
                ((Man) selectable).setTask(patrol);
            }
        }
        return "Patrol set successfully!";
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
        for (Selectable selectable : theSelected) {
            if (selectable instanceof Fightable && !((Soldier) selectable).isFighting()) {
                for (Selectable enemy : selectableEnemies) {
                    if (enemy instanceof Fightable && !((Soldier) enemy).isFighting()) {
                        Fight fight = new Fight(map, (Fightable) selectable, (Destructable) enemy);
                        currentMatch.addTask(fight);
                        ((Soldier) selectable).setTask(fight);
                    }
                }
            }
        }
        return null;
    }

    public String attackByXY(int x, int y) {
        if (type.range == 0) return "Selected unit is not ranged!";
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
        if (selectableEnemies.size() == 0) {
            for (Selectable selectable : theSelected) {
                AirStrike airStrike = new AirStrike((Fightable) selectable, type, x, y);
                currentMatch.addTask(airStrike);
                ((Man) selectable).setTask(airStrike);
            }
        }
        else {
            for (Selectable selectable : theSelected) {
                AirStrike airStrike = new AirStrike((Fightable) selectable, type,
                        (Destructable) selectableEnemies.get(random.nextInt() % selectableEnemies.size()), x, y);
                currentMatch.addTask(airStrike);
                ((Man) selectable).setTask(airStrike);
            }
        }
        return null;
    }

    public String digTunnel(int x, int y) {
        return null;
    }

    public String build(String equipmentName) {
        if (!type.getName().equals("Engineer")) return "The selected unit must be engineer!";
        if (!map.getCell(x , y).isPassable(null)) {
            return "The selected cell is not appropriate for building!";
        }
        int engineerCount = theSelected.size();
        EngineerBuildingType buildingType = EngineerBuildingType.getTypeByName(equipmentName);
        Monarchy monarchy = currentUser.getMonarchy();
        EngineerBuilding building;
        if (buildingType != null) {
            building = new EngineerBuilding(buildingType , currentUser , map.getCell(x , y));
        }
        switch (equipmentName) {
            case "shield" :
                if (engineerCount < 1) return "You need at least 1 engineer!";
                break;
            case "batteringRam" :
                if (engineerCount < 4) return "You need at least 4 engineers!";
                if (!(priceCheck(monarchy , buildingType))) return "Not enough gold!";
                break;
            case "siegeTower" :
                if (engineerCount < 4) return "You need at least 4 engineers!";
                if (!(priceCheck(monarchy , buildingType))) return "Not enough gold!";
                break;
            case "mobileCatapult" :
                if (engineerCount < 2) return "You need at least 2 engineers!";
                if (!(priceCheck(monarchy , buildingType))) return "Not enough gold!";
                break;
            case "staticCatapult" :
                if (engineerCount < 3) return "You need at least 3 engineers!";
                if (!(priceCheck(monarchy , buildingType))) return "Not enough gold!";
                break;
            default:
                return "Invalid equipment name!";
        }
        return null;
    }

    private boolean priceCheck(Monarchy monarchy , EngineerBuildingType buildingType) {
        if (monarchy.getGold() < buildingType.getCost())
            return false;
        EngineerBuilding building = new EngineerBuilding(buildingType , currentUser ,
                map.getCell(x , y));
        monarchy.addBuilding(building);
        monarchy.changeGold(-buildingType.getCost());
        map.getCell(x, y).setBuilding(building);
        return true;
    }

    public void disbandUnit() {
        int size = theSelected.size();
        for (Selectable selectable : theSelected) {
            currentMatch.getCurrentMonarchy().removeMan((Man) selectable);
            map.getCell(x, y).removeMan((Man) selectable);
        }
        theSelected.clear();
        currentUser.getMonarchy().changePopulation(size);
    }
}