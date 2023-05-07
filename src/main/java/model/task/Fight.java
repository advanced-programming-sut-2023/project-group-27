package model.task;

import model.Destructable;
import model.man.Man;

public class Fight extends Task {
    private Destructable target;

    @Override
    public void run() {

    }

    public Fight(Destructable target , Man taskOwner) {
        super(taskOwner);
        this.target = target;
    }
}
