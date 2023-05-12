package model;

public interface Fightable extends Selectable {
    void fight(Destructable destructable);
    void fight(Location location);
    int getHitPoint();

    int getAttackRange();
}
