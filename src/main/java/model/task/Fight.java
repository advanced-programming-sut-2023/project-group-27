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
        return false;
    }
}
