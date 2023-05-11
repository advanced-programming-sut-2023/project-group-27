package model.building;

import model.Destructable;
import model.Fightable;
import model.User;

public class FightableBuilding extends Building implements Fightable {
    private int damage;
    public FightableBuilding(int hitpoint, User owner , int damage) {
        super(hitpoint, owner);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void fight(Destructable destructable) {

    }
}
