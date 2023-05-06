package model;

import model.building.Building;
import model.building.Storage;
import model.man.Man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Monarchy {
    private final List<Building> buildings = new ArrayList<>();
    private final List<GoodsType> foodTypes = new ArrayList<>();
    private final List<Man> men = new ArrayList<>();
    private final Storage[] storages = new Storage[3];
    private final TradingSystem tradingSystem;
    private final User king;
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

    public int getTaxRate() {
        return taxRate;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public int getFearRate() {
        return fearRate;
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

    public void addAllMen(Man[] menToBeAdded) {
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

    public HashMap<GoodsType, Integer> getStorage() {
        HashMap<GoodsType, Integer> output = new HashMap<>();
        output.putAll(storages[0].getMap());
        output.putAll(storages[1].getMap());
        output.putAll(storages[2].getMap());
        return output;
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

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public int calcPopularity() {
        // TODO implement here
        return 0;
    }

    public void addBuilding(Building building) {
      buildings.add(building);
    }
    public void removeBuilding(Building building) {
        this.buildings.remove(building);
    }

    public void removeMan(Man man) {
        this.men.remove(man);
    }
}