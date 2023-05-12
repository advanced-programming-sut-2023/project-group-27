package model.task;

import model.*;

public class Fight implements Task {
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

    public Fight(GameMap map, Fightable fightable, Location location) {
        this.fightable = fightable;
        this.location = location;
        this.target = null;
    }

    @Override
    public void run() {
        if (fightable.getLocation().distance(location) > fightable.getAttackRange()) {
            if (!(fightable instanceof Movable)) return;
            new Move(map, (Movable) fightable, location.x, location.y).run();
            return;
        }
        if (target != null) {
            fightable.fight(this.target);
        } else {
            fightable.fight(map.getCell(location));
            isValid = false;
        }
    }

    @Override
    public boolean isValid() {
        if (fightable.getHitPoint() <= 0) {
            return false;
        }
        if (target.getHitpoint() <= 0) {
            return false;
        }
        return isValid;
    }
}
