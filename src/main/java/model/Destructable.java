package model;

public abstract class Destructable {
    private int hitpoint;
    public Destructable(int hitpoint) {
        this.hitpoint = hitpoint;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public void setHitpoint(int hitpoint) {
        this.hitpoint = hitpoint;
    }

    public abstract Location getLocation();

    public abstract User getOwner();
}
