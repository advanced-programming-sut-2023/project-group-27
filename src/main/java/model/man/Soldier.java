package model.man;

import model.Cell;
import model.Destructable;
import model.User;
import model.task.Task;

public class Soldier extends Man{
    private SoldierType soldierType;
    private Task task;
    private String state;
    private Integer range;
    private int damage;

    public Soldier(SoldierType soldierType, User owner) {
        super(soldierType.getHitpoint(), soldierType.getName(), owner);
        this.damage = soldierType.getDamage();
        this.range = soldierType.getRange();
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
