package model.man;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
import model.task.Task;

import java.util.HashMap;

public abstract class Man extends Destructable implements Movable {
    private final String name;
    private final User owner;
    private Task task;
    private Location location;
    private final Double movementSpeed;
    protected static HashMap<String, String> pictures = new HashMap<>();
    static {
        pictures.put("SpearMan", "");
        pictures.put("PikeMan", "");
        pictures.put("MaceMan", "");
        pictures.put("SwordsMan", "");
        pictures.put("Knight", "");
        pictures.put("BlackMOnk", "");
        pictures.put("LadderMan", "");
        pictures.put("Slave", "");
        pictures.put("Assassin", "");
        pictures.put("ArabSwordsMan", "");
        pictures.put("Archer", "");
        pictures.put("CrossBowMan", "");
        pictures.put("ArcherBow", "");
        pictures.put("Slinger", "");
        pictures.put("HorseArcher", "");
        pictures.put("FireThrower", "");
        pictures.put("Engineer", "");
        pictures.put("Lord", "");
    }

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
        cell.addMan(this);
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

    public ImageView getPicture() {
        return new ImageView(new Image(getClass().getResource("/assets/men/" + pictures.get(name)).toExternalForm()));
    }
}