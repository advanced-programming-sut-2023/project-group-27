package model.task;

import model.Pair;

public class Move implements Task {
    private Pair<Integer, Integer> destination;

    @Override
    public void run() {

    }

    public Move(int x, int y) {
        destination = new Pair<>(x, y);
    }
}
