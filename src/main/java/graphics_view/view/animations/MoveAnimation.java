package graphics_view.view.animations;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import model.Location;
import model.Movable;

import java.util.ArrayList;

public class MoveAnimation extends Transition {
    private final Movable movable;
    private final StackPane origin;
    private final StackPane destination;
    private final ArrayList<Image> moveImages;

    public MoveAnimation(Movable movable , StackPane originTile, StackPane destTile) {
        this.movable = movable;
        moveImages = movable.getMoveImages();
        this.origin = originTile;
        this.destination = destTile;
    }

    @Override
    protected void interpolate(double v) {
        double newX = v * destination.getLayoutX() + (1 - v) * origin.getLayoutX();
        double newY = v * destination.getLayoutY() + (1 - v) * origin.getLayoutY();
        int index = ((int)(v * 1000) % 3);

    }
}
