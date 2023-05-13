package model.task;


import model.Destructable;
import model.Fightable;

public abstract class Task {

    public abstract void run();
    public abstract boolean isValid();

}
