package model.task;


import model.Destructable;

public abstract class Task {
    private Destructable taskOwner;

    public abstract void run();
    public abstract boolean isValid();

    public Destructable getOwner() {
        return taskOwner;
    }
}
