package model;

import javafx.scene.image.Image;

import java.util.ArrayList;

public interface Fightable extends Selectable {
    void fight(Destructable destructable);
    void fight(Cell cell);
    int getHitPoint();

    int getAttackRange();

    Destructable getDestructable();

    ArrayList<Image> getFightImages();
}
