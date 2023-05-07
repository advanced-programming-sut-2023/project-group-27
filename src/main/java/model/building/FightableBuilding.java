package model.building;

import model.Destructable;
import model.Fightable;
import model.User;

public class FightableBuilding extends Building implements Fightable {
    public FightableBuilding(int hitpoint, User owner) {
        super(hitpoint, owner);
    }

    @Override
    public void fight(Destructable destructable) {

    }
}
