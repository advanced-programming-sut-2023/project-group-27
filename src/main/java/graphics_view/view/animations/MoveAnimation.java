package graphics_view.view.animations;

import controller.controller.Utilities;
import graphics_view.graphical_controller.GameController;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import model.Cell;
import model.Movable;
import model.man.Man;

import java.util.ArrayList;

public class MoveAnimation extends Transition {
    private final GameController controller;
    private final Movable movable;
    private final StackPane origin;
    private final StackPane destination;
    private final ArrayList<Image> moveImages;
    private final TilePane gameMap;
    private ImageView currentImage;

    public MoveAnimation(Movable movable , StackPane originTile, StackPane destTile) {
        this.controller = Utilities.getGameController();
        this.movable = movable;
        moveImages = movable.getMoveImages();
        this.origin = originTile;
        this.destination = destTile;
        gameMap = Utilities.getGameMap();
        setCycleCount(1);
        setCycleDuration(Duration.millis(3000));
        setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameMap.getChildren().remove(currentImage);
                Cell cell = GameController.tileToCell.get(destination);
                cell.addMan((Man) movable);
                controller.mountCellData(destination , cell);
            }
        });
    }

    @Override
    protected void interpolate(double v) {
        double newX = v * destination.getLayoutX() + (1 - v) * origin.getLayoutX();
        double newY = v * destination.getLayoutY() + (1 - v) * origin.getLayoutY();
        int index = ((int)(v * 1000) % 1);
        gameMap.getChildren().remove(currentImage);
        controller.mountCellData(origin ,
                GameController.tileToCell.get(origin));
        currentImage = new ImageView(moveImages.get(index));
        currentImage.setFitWidth(30.0);
        currentImage.setFitHeight(30.0);
        gameMap.getChildren().add(currentImage);
        currentImage.setManaged(false);
        currentImage.setLayoutX(newX);
        currentImage.setLayoutY(newY);
    }
}
