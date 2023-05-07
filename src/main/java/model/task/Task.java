package model.task;

import model.man.Man;

public abstract class Task {
    private final Man taskOwner;

    protected Task(Man taskOwner) {
        this.taskOwner = taskOwner;
    }

    public Man getTaskOwner() {
        return taskOwner;
    }
    public abstract void run();
}
