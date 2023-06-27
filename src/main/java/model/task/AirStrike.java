package model.task;

import model.Destructable;
import model.Fightable;
import model.building.FightableBuilding;
import model.man.Soldier;
import model.man.SoldierType;

public class AirStrike extends Task{
    private final Fightable fightable;
    private final SoldierType type;
    private Destructable target;
    private final int x;
    private final int y;
    private boolean isValid = true;

    public AirStrike(Fightable fightable, SoldierType type, Destructable target , int x , int y) {
        this.fightable = fightable;
        this.type = type;
        this.target = target;
        this.x = x;
        this.y = y;
    }

    public AirStrike(Fightable fightable, SoldierType type , int x , int y) {
        this.fightable = fightable;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        int xUnit = fightable.getLocation().x;
        int yUnit = fightable.getLocation().y;
        int distance = Math.abs(x - xUnit) + Math.abs(y - yUnit);
        if (distance > type.range) {
            isValid = false;
            return;
        }
        if (target == null ) return; //TODO simply play animation
        if (target.getHitpoint() <= 0) return;
        if (fightable instanceof Soldier) {
            target.setHitpoint(target.getHitpoint() - ((Soldier) fightable).getDamage());
        }
        else if (fightable instanceof FightableBuilding) {
            target.setHitpoint(target.getHitpoint() - ((FightableBuilding) fightable).getDamage());
        }
    }

    @Override
    public boolean isValid() {
        if (((Destructable) fightable).getHitpoint() <= 0) return false;
        return isValid;
    }

    public Fightable getOwner() {
        return fightable;
    }
}
