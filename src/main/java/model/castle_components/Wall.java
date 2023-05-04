package model.castle_components;

import model.User;

public class Wall extends CastleComponent{
    public Wall(int hitpoint, CastleComponentType castleComponentType, User owner) {
        super(hitpoint, castleComponentType, owner);
    }
}