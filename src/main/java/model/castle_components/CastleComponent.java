package model.castle_components;

import model.User;
import model.building.Building;

public class CastleComponent extends Building {
    private final CastleComponentType castleComponentType;
    public CastleComponent(CastleComponentType castleComponentType, User owner) {
        super(castleComponentType.getHitpoint(), owner);
        this.castleComponentType = castleComponentType;
    }

    public CastleComponentType getCastleComponentType() {
        return castleComponentType;
    }
}