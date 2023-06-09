package model;

import javafx.scene.layout.StackPane;
import model.building.Building;
import model.building.FightableBuilding;
import model.man.Man;
import model.man.Soldier;
import model.man.SoldierType;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Passable{
    private LandType type;
    private NaturalEntityType naturalEntityType;
    private Building building;
    private final ArrayList<Man> men;
    private final ArrayList<Selectable> selectables;
    private final Location location;
    public Cell(LandType type, int xCoordinate, int yCoordinate) {
        selectables = new ArrayList<>();
        men = new ArrayList<>();
        this.type = type;
        this.location = new Location(xCoordinate, yCoordinate);
    }

    public Cell(LandType type, Location location) {
        selectables = new ArrayList<>();
        men = new ArrayList<>();
        this.type = type;
        this.location = new Location(location.x, location.y);
    }

    @Override
    public boolean isPassable(Movable movable) {
        if (this.building != null) {
            return building.isPassable(movable);
        }
        if (this.naturalEntityType != null) {
            return naturalEntityType.isPassable(movable);
        }
        return type.isPassable(movable);
    }

    public LandType getType() {
        return type;
    }

    public NaturalEntityType getNaturalEntityType() {
        return naturalEntityType;
    }

    public Building getBuilding() {
        return building;
    }

    public Man getMan() {
        if (men.size() == 0) {
            return null;
        }
        return men.get(0);
    }

    public ArrayList<Man> getMen() {
        return men;
    }

    public void addMan(Man man) {
        man.setLocation(this.location);
        men.add(man);
        selectables.add(man);
    }

    public void addMen(Man[] menToBeAdded) {
        men.addAll(List.of(menToBeAdded));
        selectables.addAll(List.of(menToBeAdded));
    }
    public void removeMan(Man man) {
        men.remove(man);
        selectables.remove(man);
    }

    public int getXCoordinate() {
        return location.x;
    }

    public int getYCoordinate() {
        return location.y;
    }

    public Location getLocation() {
        return location;
    }

    public void setNaturalEntityType(NaturalEntityType naturalEntityType) {
        this.naturalEntityType = naturalEntityType;
    }

    public void setBuilding(Building building) {
        this.building = building;
        if (building instanceof FightableBuilding)
            selectables.add((Selectable) building);
    }

    public void setType(LandType type) {
        this.type = type;
    }

    public ArrayList<Selectable> getSelectables() {
        return selectables;
    }

    public void clear() {
        type = LandType.PLAIN;
        naturalEntityType = null;
        if (building != null){
            building.getOwner().getMonarchy().removeBuilding(building);
            building = null;
        }
        for (Man man : this.men)
            man.getOwner().getMonarchy().removeMan(man);
        men.clear();
    }

    public String showDetails() {
        String output = "";
        output += "Land Type: "  + this.getType().getTypeName() + "\n";
        if (this.getNaturalEntityType() != null)
            output += "NaturalEntity(Tree or Rocks) Type: " + this.getNaturalEntityType().getNaturalEntityName() + "\n";
        if (this.getBuilding() != null)
            output += "Building Type: " + this.getBuilding().getName() + ", owner: " + this.getBuilding().getOwner().getUsername() + ", hitpoints: " + this.getBuilding().getHitpoint() + "\n";
        if (this.getMen() != null && this.getMen().size() != 0)
            for (Man man : this.getMen()) {
                output += "Human Type: " + man.getName() + ", owner: " + man.getOwner().getUsername() + ", hitpoints: " + man.getHitpoint();
                if (man instanceof Soldier)
                    output += ", state: " + ((Soldier) man).getState();
                if (man.getTask() != null)
                    output += ", task: " + man.getTask().toString();
                output += "\n";
            }
        return output;
    }

    public void flush() {
        building = null;
        naturalEntityType = null;
        type = LandType.PLAIN;
    }
}