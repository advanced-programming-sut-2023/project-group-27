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
                Man.class.getResource("/assets/men/Lord.png").toExternalForm()));
        setCycleCount(1);
        setCycleDuration(Duration.millis(3000));
        setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameMap.getChildren().remove(arrowImage);
                Cell cell = GameController.tileToCell.get(destination);
                controller.mountCellData(destination , cell);
            }
        });
    }
    @Override
    protected void interpolate(double v) {
        double newX = v * destination.getLayoutX() + (1 - v) * origin.getLayoutX();
        double newY = v * destination.getLayoutY() + (1 - v) * origin.getLayoutY();
        arrowImage.setManaged(false);
        arrowImage.setLayoutX(newX);
        arrowImage.setLayoutY(newY);
    }
}
