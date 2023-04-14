package model.task;

import model.Pair;

public class Patrol implements Task {
    private Pair<Integer, Integer> destination1, destination2;

    @Override
    public void run() {

    }

    public Patrol(int x1, int y1, int x2, int y2) {
        destination1 = new Pair<>(x1, y1);
        destination2 = new Pair<>(x2, y2);
    }
}
