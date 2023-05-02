package model.building;

import model.GoodsType;

import java.util.HashMap;

public class Storage extends Building{
    private final HashMap<GoodsType, Integer> goodsMap;

    public Storage(GoodsType[] goodsTypes, int hitpoint) {
        super(hitpoint);
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

    public Integer getGoodsCount(GoodsType goodsType) {
        return goodsMap.get(goodsType);
    }

    public boolean contains(GoodsType goodsType) {
        return goodsMap.containsKey(goodsType);
    }
}