package model;

import model.building.Building;
import model.building.FightableBuilding;
import model.man.Man;
import model.man.Soldier;
import model.task.*;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private final GameMap currentMatchMap;
    private final List<Monarchy> monarchies;
    private final List<User> users;
    private Monarchy currentMonarchy;
    private int turnNumber;
    private final List<Task> taskList = new ArrayList<>();

    public Match(GameMap currentMatchMap, List<User> users) {
        this.currentMatchMap = currentMatchMap;
        this.users = users;
        monarchies = new ArrayList<>();
        users.forEach(x -> monarchies.add(x.getMonarchy()));
        this.currentMonarchy = monarchies.get(0);
        this.turnNumber = 0;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    private void runTasks() {
        for (Task task : taskList) {
            task.run();
        }
        for (Monarchy monarchy: monarchies) {
            for (Man man: monarchy.getMen()) {
                if (man instanceof Soldier) {
                    Soldier soldier = (Soldier) man;
                    if (soldier.getTask() != null && !(soldier.getTask().isValid())) {
                        soldier.setTask(null);
                    }
                }
            }
            for (Building building : monarchy.getBuildings().stream()
                    .filter(building -> building instanceof FightableBuilding).toList()) {
                FightableBuilding fightableBuilding = (FightableBuilding) building;
                if (fightableBuilding.getTask() != null && !(fightableBuilding.getTask().isValid())) {
                    fightableBuilding.setTask(null);
                }
            }
        }
        taskList.removeIf(task -> !(task.isValid()));

    }

    private void updateTasks() {
        ArrayList<Task> newTasks = new ArrayList<>();
        for (Task task : taskList.stream().filter(x -> x instanceof Fight).toList()) {
            Fight fight = (Fight) task;
            Soldier target = fight.getTarget();
            if (target == null) continue;
            if (target.getTask() != null && target.getTask() instanceof Move) {
                Task targetTask = new Fight(currentMatchMap , target , ((Fight) task).getOwner().getDestructable());
                newTasks.add(targetTask);
                target.setTask(targetTask);
            }
        }
        for (Task task : taskList) {
            Destructable owner;
            if (task instanceof Fight) {
                owner = ((Fight) task).getOwner().getDestructable();
            }
            else if (task instanceof Move) {
                owner = ((Move) task).getOwner().getDestructable();
            }
            else if (task instanceof Patrol) {
                owner = ((Patrol) task).getOwner().getDestructable();
            }
            else {
                owner = ((AirStrike) task).getOwner().getDestructable();
            }
            if (owner instanceof Soldier) {
                if (((Soldier) owner).getTask() != task) continue;
            }
            if (owner instanceof FightableBuilding) {
                if (((FightableBuilding) owner).getTask() != task) continue;
            }
            if (!task.isValid()) continue;
            newTasks.add(task);
        }
        taskList.clear();
        taskList.addAll(newTasks);
    }

    public User getCurrentUser() {
        return users.get(turnNumber % users.size());
    }

    public Monarchy getCurrentMonarchy() {
        return currentMonarchy;
    }

    public GameMap getCurrentMatchMap() {
        return currentMatchMap;
    }

    public List<Monarchy> getMonarchies() {
        return monarchies;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public String nextTurn() {
        turnNumber++;
        if (monarchies.size() <= 1) {
            for (Monarchy monarchy: monarchies) {
                monarchy.getKing().setHighScore(monarchy.getPopularity());
            }
            return "Game Over";
        }
        currentMonarchy = monarchies.get(turnNumber % monarchies.size());
        if (turnNumber % monarchies.size() == 0) {
            updateTasks();
            runTasks();
            for (Monarchy monarchy : monarchies) {
                if (monarchy.isDead()) {
                    users.remove(monarchy.getKing());
                    for (Building building: monarchy.getBuildings()) {
                        building.destroy();
                        building.getCell().setBuilding(null);
                    }
                    for (Man man: monarchy.getMen()) {
                        man.destroy();
                        currentMatchMap.getCell(man.getLocation()).getMen().remove(man);
                    }
                    monarchy.getKing().setHighScore(monarchy.getPopularity());
                }
            }
            monarchies.removeIf(Monarchy::isDead);
            for (Monarchy monarchy : monarchies) {
                monarchy.run();
                for (Man man: monarchy.getMen()) {
                    if (man.getHitpoint() <= 0) {
                        currentMatchMap.getCell(man.getLocation()).getMen().remove(man);
                    }
                }
                monarchy.getMen().removeIf(m -> m.getHitpoint() <= 0);
                for (Building building: monarchy.getBuildings()) {
                    if (building.getHitpoint() <= 0) {
                        currentMatchMap.getCell(building.getLocation()).setBuilding(null);
                    }
                }
                monarchy.getBuildings().removeIf(b -> b.getHitpoint() <= 0);
            }
        }
        return "Turn " + (turnNumber + 1) + " : " + getCurrentUser().getUsername() + "'s turn";
    }

    public User getUserByName(String otherUserName) {
        User user = null;
        for (User user1 : users) {
            if (user1.getUsername().equals(otherUserName)) {
                user = user1;
                break;
            }
        }
        return user;
    }
}