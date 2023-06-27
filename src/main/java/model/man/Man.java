package model.man;

import graphics_view.graphical_controller.GameController;
import graphics_view.view.animations.MoveAnimation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.*;
import model.task.Task;

import java.util.ArrayList;

public abstract class Man extends Destructable implements Movable {
    private final String name;
    private final User owner;
    private Task task;
    private Location location;
    private final Double movementSpeed;

    public Man(int hitpoint, String name, User owner, Double movementSpeed) {
        super(hitpoint);
        this.movementSpeed = movementSpeed;
        this.name = name;
        this.owner = owner;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void move(Cell cell, GameMap map) {
        if (location == null)return;
        Cell origin = map.getCell(location);
        origin.removeMan(this);
        //TODO mount cell in here
        MoveAnimation animation = new MoveAnimation(this ,
                GameController.cellToTile.get(origin) ,
                GameController.cellToTile.get(cell));
        animation.play();
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Double getMovementSpeed() {
        return movementSpeed;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public ImageView getPicture(Pane pane) {
        Image image = new Image(getClass().getResource("/assets/men/" + name + ".png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.fitHeightProperty().bind(pane.heightProperty());
        imageView.fitWidthProperty().bind(pane.widthProperty());
        return imageView;
    }

    @Override
    public ArrayList<Image> getMoveImages() {
        ArrayList<Image> moveImages = new ArrayList<>();
        for (int i = 1 ; i <= 1 ; i++) {
            moveImages.add(new Image(
                    Man.class.getResource("/assets/men/" + name + ".png").toExternalForm()));
        }
        return moveImages;
    }
}