package model.task;

import model.Location;
import model.man.Man;

public class Move extends Task {
    private Location destination;

    @Override
    public void run() {

    }

    public Move(int x, int y , Man taskOwner) {
        super(taskOwner);
        destination = new Location(x, y);
    }
}
