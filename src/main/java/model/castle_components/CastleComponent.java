package model.castle_components;

import model.building.Building;

public class CastleComponent extends Building {
    private CastleComponentType castleComponentType;
    public CastleComponent(int hitpoint, CastleComponentType castleComponentType) {
        super(hitpoint);
        this.castleComponentType = castleComponentType;
    }

    public CastleComponentType getCastleComponentType() {
        return castleComponentType;
    }
}
