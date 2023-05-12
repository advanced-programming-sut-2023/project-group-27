package model.castle_components;

import model.Cell;
import model.Location;
import model.User;
import model.building.Building;

public class CastleComponent extends Building {
    private final CastleComponentType castleComponentType;
    public CastleComponent(CastleComponentType castleComponentType, User owner, Cell cell) {
        super(castleComponentType.getHitpoint(), owner, cell, castleComponentType.getName());
        this.castleComponentType = castleComponentType;
    }

    public CastleComponentType getCastleComponentType() {
        return castleComponentType;
    }
}