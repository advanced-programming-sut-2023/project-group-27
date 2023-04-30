package model.man;

import model.User;

public class MeeleSoldier extends Soldier{
    private MeeleType meeleType;

    public MeeleSoldier(MeeleType meeleType, User owner) {
        super(meeleType.getHitpoint(), meeleType.getName(), meeleType.getDamage(), owner);
        this.meeleType = meeleType;
    }
}