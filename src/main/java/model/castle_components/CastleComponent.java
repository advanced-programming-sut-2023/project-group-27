package model.castle_components;

import model.Location;
import model.User;
import model.building.Building;

public class CastleComponent extends Building {
    private final CastleComponentType castleComponentType;
    public CastleComponent(CastleComponentType castleComponentType, User owner, Location location) {
        super(castleComponentType.getHitpoint(), owner, location);
        this.castleComponentType = castleComponentType;
    }

    public CastleComponentType getCastleComponentType() {
        return castleComponentType;
    }
}