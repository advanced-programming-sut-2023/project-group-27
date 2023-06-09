package model.man;

import graphics_view.graphical_controller.GameController;
import graphics_view.view.animations.FightAnimation;
import javafx.scene.image.Image;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Soldier extends Man implements Fightable {
    private SoldierType soldierType;
    private String state;
    private Integer range;
    private int damage;
    private boolean isFighting;

    public Soldier(SoldierType soldierType, User owner) {
        super(soldierType.getHitpoint(), soldierType.getName(), owner,
                soldierType.getMovementSpeed());
        this.soldierType = soldierType;
        this.damage = soldierType.getDamage();
        this.range = soldierType.getRange();
        this.state = "standing";
        isFighting = false;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public boolean isFighting() {
        return isFighting;
    }

    @Override
    public void setFighting(boolean fighting) {
        isFighting = fighting;
    }

    @Override
    public void fight(Destructable destructable) {
        if (destructable.getOwner() == getOwner()) {
            return;
        }
        Cell cell = StrongholdCrusader.getCurrentMatch().
                getCurrentMatchMap().getCell(destructable.getLocation());
        isFighting = true;
        FightAnimation animation = new FightAnimation(this , GameController.cellToTile.get(cell));
        animation.play();
        destructable.setHitpoint(destructable.getHitpoint() - damage);
    }

    @Override
    public void fight(Cell cell) {
        if (cell.getBuilding() != null && cell.getBuilding().getOwner() != getOwner()) {
            fight(cell.getBuilding());
            return;
        }
        List<Man> enemies = cell.getMen().stream()
                .filter(m -> m.getOwner() != getOwner()).collect(Collectors.toList());
        if (enemies.size() == 0) {
            return;
        }
        Random random = new Random();
        fight(enemies.get(random.nextInt(enemies.size())));
    }

    public int getAttackRange() {
        return Objects.requireNonNullElse(range, 0);
    }

    @Override
    public Destructable getDestructable() {
        return this;
    }

    @Override
    public int getHitPoint() {
        return super.getHitpoint();
    }

    @Override
    public ArrayList<Image> getFightImages() {
        ArrayList<Image> fightImages = new ArrayList<>();
        for (int i = 1 ; i <= 1 ; i++) {
            fightImages.add(new Image(
                    Man.class.getResource("/assets/men/" + super.getName() + ".png").toExternalForm()));
        }
        return fightImages;
    }

}
