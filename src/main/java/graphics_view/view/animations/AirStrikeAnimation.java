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
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.Cell;
import model.man.Man;

public class AirStrikeAnimation extends Transition {
    private final GameController controller;
    private final StackPane origin;
    private final StackPane destination;
    private final TilePane gameMap;
    private final ImageView arrowImage;

    public AirStrikeAnimation(StackPane originTile, StackPane destTile) {
        this.controller = Utilities.getGameController();
        this.origin = originTile;
        this.destination = destTile;
        gameMap = Utilities.getGameMap();
        arrowImage = new ImageView(new Image(
                Man.class.getResource("/assets/animation/arrow.png").toExternalForm()));
        gameMap.getChildren().add(arrowImage);
        arrowImage.setManaged(false);
        double deltaX = destTile.getLayoutX() - originTile.getLayoutX();
        double deltaY = destTile.getLayoutY() - originTile.getLayoutY();
        double angle;
        if (deltaX != 0) {
            angle = Math.toDegrees(Math.atan(deltaY / deltaX));
        }
        else {
            angle = 90;
        }
        arrowImage.getTransforms().add(new Rotate(angle + 90));
        setCycleCount(1);
        setCycleDuration(Duration.millis(1000));
        setOnFinished(actionEvent -> {
            gameMap.getChildren().remove(arrowImage);
            Cell cell = GameController.tileToCell.get(destination);
            controller.mountCellData(destination, cell);
        });
    }
    @Override
    protected void interpolate(double v) {
        double newX = v * destination.getLayoutX() + (1 - v) * origin.getLayoutX();
        double newY = v * destination.getLayoutY() + (1 - v) * origin.getLayoutY();
        arrowImage.setFitWidth(30.0);
        arrowImage.setFitHeight(30.0);
        arrowImage.setLayoutX(newX);
        arrowImage.setLayoutY(newY);
    }
}
