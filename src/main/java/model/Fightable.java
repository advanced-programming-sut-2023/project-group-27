package model;

public interface Fightable extends Selectable {
    public void fight(Destructable destructable);
    public int getHitPoint();
}
