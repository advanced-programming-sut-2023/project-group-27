package model.task;

import model.Location;

public class Move implements Task {
    private Location destination;

    @Override
    public void run() {

    }

    public Move(int x, int y) {
        destination = new Location(x, y);
    }
}
