package model.task;

import model.Location;
import model.man.Man;

public class Patrol extends Task {
    private Location destination1, destination2;

    @Override
    public void run() {

    }

    public Patrol(int x1, int y1, int x2, int y2 , Man taskOwner) {
        super(taskOwner);
        destination1 = new Location(x1, y1);
        destination2 = new Location(x2, y2);
    }
}
