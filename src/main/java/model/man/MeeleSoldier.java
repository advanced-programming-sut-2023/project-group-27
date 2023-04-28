package model.man;

import model.User;

public class MeeleSoldier extends Soldier{
    private MeeleType meeleType;

    public MeeleSoldier(int hitpoint, int damage, MeeleType meeleType, User owner) {
        super(hitpoint, meeleType.getName(), damage, owner);
    }
}