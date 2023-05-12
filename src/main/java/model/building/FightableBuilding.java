package model.building;

import model.*;

public class FightableBuilding extends Building implements Fightable {
    public FightableBuilding(int hitpoint, User owner, Cell cell) {
        super(hitpoint, owner, cell);
    }

    @Override
    public void fight(Destructable destructable) {
        // TODO implement here
    }

    @Override
    public void fight(Location location) {
        // TODO implement here
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