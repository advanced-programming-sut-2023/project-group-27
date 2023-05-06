package model.castle_components;

import model.User;
import model.building.Building;

public class CastleComponent extends Building {
    private CastleComponentType castleComponentType;
    public CastleComponent(int hitpoint, CastleComponentType castleComponentType, User owner) {
        super(hitpoint, owner);
        this.castleComponentType = castleComponentType;
    }

    public CastleComponentType getCastleComponentType() {
        return castleComponentType;
    }
}
