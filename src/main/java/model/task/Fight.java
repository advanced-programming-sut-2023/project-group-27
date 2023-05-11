package model.task;

import model.Destructable;
import model.Fightable;

public class Fight implements Task {
    private final Fightable fightable;
    private final Destructable target;

    public Fight(Fightable fightable , Destructable target) {
        this.fightable = fightable;
        this.target = target;
    }

    @Override
    public void run() {

    }

    @Override
    public boolean isValid() {
        if (fightable.getHitPoint() <= 0) {
            return false;
        }
        if (target.getHitpoint() <= 0) {
            return false;
        }
        return true;
    }
}
