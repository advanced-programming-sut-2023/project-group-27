package model.castle_components;

import model.Cell;
import model.User;

public class Tower extends CastleComponent{
    public Tower(CastleComponentType castleComponentType, User owner, Cell cell) {
        super(castleComponentType, owner, cell);
    }
}
