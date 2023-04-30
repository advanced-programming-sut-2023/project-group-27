package model;

import model.building.Building;
import model.building.Storage;
import model.man.Man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Monarchy {
    private final List<Building> buildings = new ArrayList<>();
    private final List<GoodsType> foodTypes = new ArrayList<>();
    private final List<Man> men = new ArrayList<>();
    private final Storage[] storages = new Storage[3];
    private final TradingSystem tradingSystem;
    private User king;
    private int popularity;
    private int taxRate;
    private int foodRate;
    private int gold;
    private int fearRate;

    public Monarchy(User king) {
        this.king = king;
        this.tradingSystem = new TradingSystem(king);
        this.popularity = 50;
        this.taxRate = 0;
        this.foodRate = 0;
        this.gold = 0;
        this.fearRate = 0;
    }

    public User getKing() {
        return king;
    }

    public int getGold() {
        return gold;
    }

    public void changeGold(int amount) {
        gold += amount;
    }

    public void addallMen(Man[] menToBeAdded) {
        men.addAll(List.of(menToBeAdded));
    }

    public TradingSystem getTradingSystem() {
        return tradingSystem;
    }

    public Storage getGranary() {
        return storages[0];
    }

    public Storage getStockPile() {
        return storages[1];
    }

    public Storage getArmoury() {
        return storages[2];
    }

    public int getGood(GoodsType goodsType) {
        Integer amount;
        for (Storage thisStorage : storages) {
            if ((amount = thisStorage.getGoodsCount(goodsType)) != null)
                return amount;
        }
        return 0;
    }

    public void putGood(GoodsType goodsType, int number) {
        for (Storage thisStorage : storages) {
            if (thisStorage.contains(goodsType))
                thisStorage.putGoodsCount(goodsType, number);
        }
    }
}