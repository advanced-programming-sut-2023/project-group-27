package model.building;

import model.*;

import java.util.List;
import java.util.Random;

public class FightableBuilding extends Building implements Fightable {
    private int damage;
      
    public FightableBuilding(int hitpoint, User owner, Cell cell, int damage) {
        super(hitpoint, owner, location);
        this.damage = damage;
    }
    
    public int getDamage() {
        return damage;
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
                .filter(m -> m.getOwner() != getOwner()).toList();
        if (enemies.size() == 0) {
            return;
        }
        Random random = new Random();
        fight(enemies.get(random.nextInt(enemies.size())));
    }

    @Override
    public int getHitPoint() {
        return super.getHitpoint();
    }

    @Override
    public int getAttackRange() {
        // TODO implement here
        return 0;
    }
}