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
import model.Fightable;
import model.man.Man;

import java.awt.*;
import java.util.ArrayList;

public class FightAnimation extends Transition {
    private final GameController controller;
    private final Fightable fightable;
    private final StackPane tile;
    private final TilePane gameMap;
    private final ArrayList<Image> fightImages;
    private ImageView currentImage;

    public FightAnimation(Fightable fightable , StackPane tile) {
        this.controller = Utilities.getGameController();
        this.fightable = fightable;
        this.tile = tile;
        this.gameMap = Utilities.getGameMap();
        fightImages = fightable.getFightImages();
        setCycleCount(1);
        setCycleDuration(Duration.millis(3000));
        setOnFinished(actionEvent -> {
            gameMap.getChildren().remove(currentImage);
            Cell cell = GameController.tileToCell.get(tile);
            controller.mountCellData(tile , cell);
        });
    }

    @Override
    protected void interpolate(double v) {
        gameMap.getChildren().remove(currentImage);
        controller.mountCellData(tile ,
                GameController.tileToCell.get(tile));
        if (v <= 0.33) currentImage = new ImageView(fightImages.get(0));
        if (v > 0.33 && v <= 0.66) currentImage = new ImageView(fightImages.get(0));
        if (v > 0.66) currentImage = new ImageView(fightImages.get(0));
        currentImage.setFitWidth(30.0);
        currentImage.setFitHeight(30.0);
        gameMap.getChildren().add(currentImage);
        currentImage.setManaged(false);
    }
}
