package model.man;

import model.*;
import model.task.Task;

import java.util.Objects;

public class Soldier extends Man implements Fightable {
    private SoldierType soldierType;
    private Task task;
    private String state;
    private Integer range;
    private int damage;
    private boolean isFighting;

    public Soldier(SoldierType soldierType, User owner) {
        super(soldierType.getHitpoint(), soldierType.getName(), owner, soldierType.getMovementSpeed());
        this.damage = soldierType.getDamage();
        this.range = soldierType.getRange();
        this.state = "standing";
        isFighting = false;
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

    public boolean isFighting() {
        return isFighting;
    }

    public void setFighting(boolean fighting) {
        isFighting = fighting;
    }

    @Override
    public void fight(Destructable destructable) {
        // TODO implement here
    }

    @Override
    public void fight(Location location) {
        // TODO implement here
    }

    public int getAttackRange() {
        return Objects.requireNonNullElse(range, 0);
    }

    @Override
    public int getHitPoint() {
        return super.getHitpoint();
    }

}
