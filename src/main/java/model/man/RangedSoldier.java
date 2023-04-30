package model.man;

import model.User;

public class RangedSoldier extends Soldier{
    private RangedType rangedType;
    private int range;

    public RangedSoldier(RangedType rangedType, User owner) {
        super(rangedType.getHitpoint(), rangedType.getName(), rangedType.getDamage(), owner);
        this.rangedType = rangedType;
        range = rangedType.getRange();
    }


    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean isOnTopOfTower() {
        return false;
    }
}
