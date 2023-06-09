package model.building;

import javafx.scene.image.Image;
import model.*;
import model.man.Man;
import model.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FightableBuilding extends Building implements Fightable {
    private int damage;
    private Task task;
    private boolean isFighting;
      
    public FightableBuilding(int hitpoint, User owner, Cell cell, int damage) {
        super(hitpoint, owner, cell, "fighterBuilding");
        this.damage = damage;
        isFighting = false;
    }
    
    public int getDamage() {
        return damage;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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

    @Override
    public int getHitPoint() {
        return super.getHitpoint();
    }

    @Override
    public int getAttackRange() {
        return 0;
    }

    @Override
    public Destructable getDestructable() {
        return this;
    }

    @Override
    public ArrayList<Image> getFightImages() {
        ArrayList<Image> fightImages = new ArrayList<>();
        for (int i = 1 ; i <= 1 ; i++) {
            fightImages.add(new Image(
                    Man.class.getResource("/assets/buildings/" + super.getName() + ".png").toExternalForm()));
        }
        return fightImages;
    }
}