package model.building;

import model.GoodsType;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage extends Building{
    private final HashMap<GoodsType, Integer> goodsMap;

    public Storage(GoodsType[] goodsTypes) {
        goodsMap = new HashMap<>();

        for (GoodsType goodsType : goodsTypes)
            goodsMap.put(goodsType, 0);
    }

    public void modifyGoodsCount(GoodsType goodsType, int number) {
        goodsMap.put(goodsType, goodsMap.get(goodsType) + number);
    }

    public int getGoodsCount(GoodsType goodsType) {
        return goodsMap.get(goodsType);
    }
}