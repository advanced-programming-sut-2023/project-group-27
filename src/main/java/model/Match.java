package model;

import java.util.ArrayList;

public class Match {
    private final GameMap currentMatchMap;
    private final ArrayList<Monarchy> monarchies;
    private Monarchy currentMonarchy;
    private int turnNumber;

    public Match(GameMap currentMatchMap, ArrayList<Monarchy> monarchies) {
        this.currentMatchMap = currentMatchMap;
        this.monarchies = monarchies;
        this.turnNumber = 0;
    }

    public Monarchy getCurrentMonarchy() {
        return currentMonarchy;
    }

    public GameMap getCurrentMatchMap() {
        return currentMatchMap;
    }

    public ArrayList<Monarchy> getMonarchies() {
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