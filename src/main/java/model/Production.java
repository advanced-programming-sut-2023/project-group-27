package model;

import model.building.Storage;

public enum Production {
    ;
    private Goods good;
    private Storage storage;
    private int amount;
    Production(Goods good, Storage storage, int amount) {
        this.amount = amount;
        this.good = good;
        this.storage = storage;
    }

    public void produce() {

    }

    public void utilize() {

    }
}
