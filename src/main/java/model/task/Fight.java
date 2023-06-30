package model.task;

import graphics_view.view.animations.FightAnimation;
import model.*;
import model.man.Soldier;

public class Fight extends Task {
    private final Fightable fightable;
    private final Destructable target;
    private final Location location;
    private GameMap map;
    private boolean isValid = true;

    public Fight(GameMap map, Fightable fightable , Destructable target) {
        this.fightable = fightable;
        this.target = target;
        this.location = target.getLocation();
        this.map = map;
    }

    public Soldier getTarget() {
        if (target instanceof Soldier) return (Soldier) target;
        return null;
    }

    public Fightable getOwner() {
        return fightable;
    }

    @Override
    public void run() {
        if (fightable.getLocation().distance(target.getLocation()) > fightable.getAttackRange()) {
            if (!(fightable instanceof Movable)) return;
            new Move(map, (Movable) fightable, target.getLocation().x, target.getLocation().y).run();
            return;
        }
        fightable.fight(this.target);
    }

    @Override
    public boolean isValid() {
        if (fightable.getHitPoint() <= 0) {
            fightable.setFighting(false);
            return false;
        }
        if (target.getHitpoint() <= 0) {
            fightable.setFighting(false);
            return false;
        }
        return isValid;
    }

    @Override
    public String toString() {
        return "Fight";
    }
}
