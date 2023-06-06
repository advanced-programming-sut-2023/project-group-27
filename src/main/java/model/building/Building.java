package model.building;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;

import java.util.HashMap;

public class Building extends Destructable implements Passable {
    protected final static HashMap<String, String> pictures = new HashMap<>();
    static {
        pictures.put("Hovel","Hovel.gif");
        pictures.put("Stable","Stables.png");
        pictures.put("Cathedral", "Cathedral.gif");
        pictures.put("Church", "Church.gif");
        pictures.put("Barracks", "Barracks.png");
        pictures.put("MercenaryPost", "MercenaryPost.png");
        pictures.put("EngineersGuild", "EngineersGuild.gif");
        pictures.put("batteringRam",);
        pictures.put("siegeTower",);
        pictures.put("fireThrower",);
        pictures.put("staticCatapult",);
        pictures.put("mobileCatapult",);
        pictures.put("shield",);
        pictures.put("AppleFarm", "AppleFarm.gif");
        pictures.put("DairyFarm", "DairyFarm.gif");
        pictures.put("HopsFarm", "HopsFarm.gif");
        pictures.put("HuntingPost", "HuntingPost.gif");
        pictures.put("WheatFarm","WheatFarm.png");
        pictures.put("Bakery", "Bakery.png");
        pictures.put("Brewery", "Brewery.png");
        pictures.put("WoodCutter","WoodCutter.png");
        pictures.put("Mill","Mill.gif");
        pictures.put("Quarry", "Quarry.png");
        pictures.put("PitchRig", "PitchRig.gif");
        pictures.put("IronMine", "IronMine.png");
        pictures.put("Armourer", "Armourer.gif");
        pictures.put("PoluternerWorkShop", "PoluternerWorkShop.gif");
        pictures.put("FletcherWorkShop", "FletcherWorkShop.gif");
        pictures.put("BlackSmith", "BlackSmith.gif");
        pictures.put("Inn",);
        pictures.put("Granary", "Granary.gif");
        pictures.put("StockPile", "StockPile.gif");
        pictures.put("Armoury", "Armoury.png");
        pictures.put("Keep", "Keep.png");
        pictures.put("BigKeep", "BigKeep.png");
        pictures.put("WatchTower", "WatchTower.png");
        pictures.put("EnvironmentTower", "EnvironmentTower.png");
        pictures.put("DefensiveTower", "DefensiveTower.png");
        pictures.put("SquareTower", "SquareTower.png");
        pictures.put("CircleTower", "CircleTower.png");
        pictures.put("ChangingBridge", );
        pictures.put("Wall", );
        pictures.put("ShortWall", );
    }
    private boolean isActive;
    private String name;
    private final User owner;
    private Cell cell;

    public Building(int hitpoint, User owner, Cell cell, String name) {
        super(hitpoint);
        this.cell = cell;
        this.owner = owner;
        this.name = name;
    }

    @Override
    public boolean isPassable(Movable movable) {
        return false;
    }

    public String getName() {
        return name;
    }

    public Cell getCell() {
        return cell;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public User getOwner() {
        return owner;
    }

    public Location getLocation() {
        return cell.getLocation();
    }

    public ImageView getPicture() {
        return new ImageView(new Image(getClass().getResource("/assets/buildings/" + pictures.get(this.name)).toExternalForm()));
    }
}