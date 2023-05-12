package model.castle_components;

import model.Cell;
import model.User;

public class Wall extends CastleComponent{
    public Wall(CastleComponentType castleComponentType, User owner, Cell cell) {
        super(castleComponentType, owner, cell);
    }
}