package model.task;

import model.Destructable;

public class Fight implements Task {
    private Destructable target;

    @Override
    public void run() {

    }

    public Fight(Destructable target) {
        this.target = target;
    }
}
