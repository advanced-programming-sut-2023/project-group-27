package model;

import model.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private final GameMap currentMatchMap;
    private final List<Monarchy> monarchies;
    private final List<User> users;
    private Monarchy currentMonarchy;
    private int turnNumber;
    private List<Task> taskList;

    public Match(GameMap currentMatchMap, List<User> users) {
        this.currentMatchMap = currentMatchMap;
        this.users = users;
        monarchies = new ArrayList<>();
        users.forEach(x -> monarchies.add(x.getMonarchy()));
        this.turnNumber = 0;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void runTasks() {
        for (Task task : taskList) {
            task.run();
        }
    }

    public void updateTasks() {
        List<Task> newList = new ArrayList<>();
        for (Task task : taskList) {
            newList.add(updatedTask(task));
        }
        taskList.clear();
        taskList.addAll(newList);
    }

    private Task updatedTask(Task task) {
        // TODO implement here
        return null;
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

    public void setCurrentMonarchy(Monarchy currentMonarchy) {
        this.currentMonarchy = currentMonarchy;
    }

    public void incrementTurn() {
        turnNumber++;
    }
}