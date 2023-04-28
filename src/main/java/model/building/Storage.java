package model.building;

import model.Goods;

import java.util.HashMap;

public class Storage extends Building{
    HashMap<Goods, Integer> goodsMap;

    public Storage(int hitpoint) {
        super(hitpoint);
    }
}
