package model.task;

import controller.view_controllers.Utilities;
import graphics_view.graphical_controller.GameController;
import graphics_view.view.animations.AirStrikeAnimation;
import model.*;
import model.building.FightableBuilding;
import model.man.Soldier;
import model.man.SoldierType;

import java.util.Map;

public class AirStrike extends Task{
    private final Fightable fightable;
    private final SoldierType type;
    private Destructable target;
    private final int x;
    private final int y;
    private boolean isValid = true;
    private final AirStrikeAnimation animation;

    public AirStrike(Fightable fightable, SoldierType type, Destructable target , int x , int y) {
        this.fightable = fightable;
        this.type = type;
        this.target = target;
        this.x = x;
        this.y = y;
        GameMap map = StrongholdCrusader.getCurrentMatch().getCurrentMatchMap();
        animation = new AirStrikeAnimation(GameController.cellToTile.get(map.getCell(fightable.getLocation())) ,
                GameController.cellToTile.get(map.getCell(target.getLocation())));
    }

    public AirStrike(Fightable fightable, SoldierType type , int x , int y) {
        this.fightable = fightable;
        this.type = type;
        this.x = x;
        this.y = y;
        GameMap map = StrongholdCrusader.getCurrentMatch().getCurrentMatchMap();
        animation = new AirStrikeAnimation(GameController.cellToTile.get(map.getCell(fightable.getLocation())) ,
                GameController.cellToTile.get(map.getCell(new Location(x , y))));
    }

    @Override
    public void run() {
        int xUnit = fightable.getLocation().x;
        int yUnit = fightable.getLocation().y;
        int distance = Math.abs(x - xUnit) + Math.abs(y - yUnit);
        if (distance > type.range) {
            isValid = false;
            return;
        }
        if (target == null ) {
            animation.play();
            return;
        }
        animation.play();
        if (target.getHitpoint() <= 0) return;
        if (fightable instanceof Soldier) {
            target.setHitpoint(target.getHitpoint() - ((Soldier) fightable).getDamage());
        }
        else if (fightable instanceof FightableBuilding) {
            target.setHitpoint(target.getHitpoint() - ((FightableBuilding) fightable).getDamage());
        }
    }

    @Override
    public boolean isValid() {
        if (((Destructable) fightable).getHitpoint() <= 0) return false;
        return isValid;
    }

    public Fightable getOwner() {
        return fightable;
    }

    @Override
    public String toString() {
        return "AirStrike";
    }
}
