package model.man;

import model.*;
import model.building.Building;
import model.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Soldier extends Man implements Fightable {
    private SoldierType soldierType;
    private String state;
    private Integer range;
    private int damage;
    private boolean isFighting;

    public Soldier(SoldierType soldierType, User owner) {
        super(soldierType.getHitpoint(), soldierType.getName(), owner, soldierType.getMovementSpeed());
        this.soldierType = soldierType;
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
        if (destructable.getOwner() == getOwner()) {
            return;
        }
        destructable.setHitpoint(destructable.getHitpoint() - damage);
    }

    @Override
    public void fight(Cell cell) {
        if (cell.getBuilding() != null && cell.getBuilding().getOwner() != getOwner()) {
            fight(cell.getBuilding());
            return;
        }
        List<Man> enemies = cell.getMen().stream()
                .filter(m -> m.getOwner() != getOwner()).collect(Collectors.toList());
        if (enemies.size() == 0) {
            return;
        }
        Random random = new Random();
        fight(enemies.get(random.nextInt(enemies.size())));
    }

    public int getAttackRange() {
        return Objects.requireNonNullElse(range, 0);
    }

    @Override
    public Destructable getDestructable() {
        return this;
    }

    @Override
    public int getHitPoint() {
        return super.getHitpoint();
    }

}
