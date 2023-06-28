package graphics_view.view.animations;

import controller.controller.Utilities;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import model.Fightable;

import java.awt.*;
import java.util.ArrayList;

public class FightAnimation extends Transition {
    private final Fightable fightable;
    private final StackPane tile;
    private final TilePane gameMap;
    private final ArrayList<Image> fightImages;

    public FightAnimation(Fightable fightable , StackPane tile) {
        this.fightable = fightable;
        this.tile = tile;
        this.gameMap = Utilities.getGameMap();
        fightImages = fightable.getFightImages();
    }

    @Override
    protected void interpolate(double v) {

    }
}
