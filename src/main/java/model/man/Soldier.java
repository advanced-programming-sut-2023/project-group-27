package model.man;

import model.Cell;
import model.Destructable;
import model.User;
import model.task.Task;

public class Soldier extends Man{
    private Task task;
    private String state;
    private int damage;

    public Soldier(int hitpoint, String name, int damage, User owner) {
        super(hitpoint, name, owner);
        this.damage = damage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void fight(Destructable destructable)
    {

    }

}
