package model.building;

import model.GoodsType;
import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Storage extends Building{
    private final HashMap<GoodsType, Integer> goodsMap;

    public Storage(GoodsType[] goodsTypes, int hitpoint, User owner) {
        super(hitpoint, owner);
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

    public HashMap<GoodsType, Integer> getMap() {
        HashMap<GoodsType, Integer> output = new HashMap<>();
        output.putAll(goodsMap);
        return output;
    }
}