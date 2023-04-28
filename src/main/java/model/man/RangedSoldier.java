package model.man;

import model.User;

public class RangedSoldier extends Soldier{
    private RangedType rangedType;
    private int range;

    public RangedSoldier(int hitpoint, int damage, RangedType rangedType, User owner) {
        super(hitpoint, rangedType.getName(), damage, owner);
    }


    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void isOnTopOfTower()
    {

    }
}
