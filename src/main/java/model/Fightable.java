package model;

public interface Fightable extends Selectable {
    void fight(Destructable destructable);
    void fight(Cell cell);
    int getHitPoint();

    int getAttackRange();
}
