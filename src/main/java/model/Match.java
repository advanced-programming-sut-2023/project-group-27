package model;

import model.building.FightableBuilding;
import model.man.Soldier;
import model.task.Fight;
import model.task.Move;
import model.task.Task;

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
    }

    private void updateTasks() {
        ArrayList<Task> newTasks = new ArrayList<>();
        for (Task task : taskList.stream().filter(x -> x instanceof Fight).toList()) {
            Fight fight = (Fight) task;
            Soldier target = fight.getTarget();
            if (target == null) continue;
            Task targetTask = target.getTask();
            if (targetTask instanceof Move) {
                targetTask = new Fight(currentMatchMap , target , task.getOwner());
                newTasks.add(targetTask);
                target.setTask(targetTask);
            }
        }
        for (Task task : taskList) {
            Destructable owner = task.getOwner();
            if (owner instanceof Soldier) {
                if (((Soldier) owner).getTask() == task) continue;
            }
            if (owner instanceof FightableBuilding) {
                if (((FightableBuilding) owner).getTask() == task) continue;
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

    public void nextTurn() {
        turnNumber++;
        currentMonarchy = monarchies.get(turnNumber % monarchies.size());
        if (turnNumber % monarchies.size() == 0) {
            runTasks();
            updateTasks();
            for (Monarchy monarchy : monarchies) {
                monarchy.run();
            }
        }
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