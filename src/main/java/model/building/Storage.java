package model.building;

import model.Cell;
import model.GoodsType;
import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Storage extends Building{
    private final HashMap<GoodsType, Integer> goodsMap;

    public Storage(GoodsType[] goodsTypes, int hitpoint, User owner, Cell cell, String name) {
        super(hitpoint, owner, cell, name);
        goodsMap = new HashMap<>();

        for (GoodsType goodsType : goodsTypes)
            goodsMap.put(goodsType, 0);
    }

    public void modifyGoodsCount(GoodsType goodsType, int number) {
        goodsMap.put(goodsType, goodsMap.get(goodsType) + number);
    }

    public void putGoodsCount(GoodsType goodsType, int value) {
        goodsMap.put(goodsType, value);
    }

    public void changeGoodsCount(GoodsType goodsType, int value) {
        goodsMap.put(goodsType, goodsMap.getOrDefault(goodsType, 0) + value);
    }

    public Integer getGoodsCount(GoodsType goodsType) {
        return goodsMap.get(goodsType);
    }

    public boolean contains(GoodsType goodsType) {
        return goodsMap.containsKey(goodsType);
    }

    public HashMap<GoodsType, Integer> getMap() {
        return goodsMap;
    }
}