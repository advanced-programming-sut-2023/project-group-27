package model.task;

import controller.controller.BFS;
import model.GameMap;
import model.Match;
import model.man.Soldier;

public class Search extends Task{
    private final Soldier soldier;
    private BFS bfs;
    private final GameMap map;
    private final Match match;
    private boolean isValid = true;

    public Search(Soldier soldier, GameMap map, Match match) {
        this.soldier = soldier;
        this.map = map;
        this.match = match;
    }

    @Override
    public void run() {
        String state = soldier.getState();
        int range = 0;
        switch (state) {
            case "defensive":
                range = 5;
                break;
            case "standing":
                range = 0;
                break;
            case "offensive":
                range = 10;
                break;
        }
        bfs = new BFS(map , soldier , range);
        Soldier target = bfs.getTarget();
        if (target == null) {
            isValid = false;
            return;
        }
        Fight fight = new Fight(map , soldier , target);
        match.addTask(fight);
        soldier.setTask(fight);
    }

    @Override
    public boolean isValid() {
        if (soldier.getHitPoint() <= 0) return false;
        return isValid;
    }

    @Override
    public String toString() {
        return "Search";
    }
}
