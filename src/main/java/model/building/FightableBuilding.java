package model.building;

import model.Destructable;
import model.Fightable;
import model.Location;
import model.User;

public class FightableBuilding extends Building implements Fightable {
    public FightableBuilding(int hitpoint, User owner, Location location) {
        super(hitpoint, owner, location);
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
