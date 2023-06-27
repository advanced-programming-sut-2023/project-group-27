package model;

import javafx.scene.image.Image;

import java.util.ArrayList;

public interface Movable extends Selectable {
    void move(Cell cell, GameMap map);
    Double getMovementSpeed();

    Destructable getDestructable();

    ArrayList<Image> getMoveImages();
}
