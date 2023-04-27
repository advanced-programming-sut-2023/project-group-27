package model.man;

public class RangedSoldier extends Soldier{
    private RangedType rangedType;
    private int range;

    public RangedSoldier(int hitpoint, int damage, RangedType rangedType) {
        super(hitpoint, rangedType.getName(), damage);
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
