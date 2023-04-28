package model.building;

import model.Destructable;
import model.Fightable;

public class FightableBuilding extends Building implements Fightable {
    public FightableBuilding(int hitpoint) {
        super(hitpoint);
    }

    @Override
    public void fight(Destructable destructable) {

    }
}
