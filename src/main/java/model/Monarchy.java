package model;

import model.building.Building;
import model.man.Man;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Monarchy {
    private List<Building> buildings = new ArrayList<>();
    private List<GoodsType> foodTypes = new ArrayList<>();
    private List<Man> men = new ArrayList<>();
    private Map<GoodsType, Integer> storage = new HashMap<>();
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

    public TradingSystem getTradingSystem() {
        return tradingSystem;
    }

    public Map<GoodsType, Integer> getStorage() {
        return storage;
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
}
