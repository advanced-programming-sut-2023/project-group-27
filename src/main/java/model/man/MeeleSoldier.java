package model.man;

public class MeeleSoldier extends Soldier{
    private MeeleType meeleType;

    public MeeleSoldier(int hitpoint, int damage, MeeleType meeleType) {
        super(hitpoint, meeleType.getName(), damage);
    }
}
