package model.castle_components;

import model.Cell;
import model.User;

public class Trap extends CastleComponent{
    public Trap(CastleComponentType castleComponentType, User owner, Cell cell) {
        super(castleComponentType, owner, cell);
    }
}