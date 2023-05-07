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
    private int popularity, taxRate, foodRate, gold, fearRate, religiousBuildingCount;

    public Monarchy(User king) {
        storages[0] = new Storage(GoodsType.getGranaryGoods(), 30000, king);
        storages[1] = new Storage(GoodsType.getStockPileGoods(), 30000, king);
        storages[2] = new Storage(GoodsType.getArmouryGoods(), 30000, king);
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

    public int getPopularity() {
        return popularity;
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
        int result = 0;
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

    public int calcPopularityFood() {
        return 4 * foodRate;
    }

    public int calcPopularityTax() {
        if (taxRate <= 0) {
            return taxRate * -2 + 1;
        }
        if (taxRate <= 4) {
            return taxRate * 2;
        }
        return taxRate * 4 - 8;
    }


    public int calcPopularityReligion() {
        // TODO update religiousBuildingCount when creating or destroying religious buildings
        return 2 * religiousBuildingCount;
    }


    public int calcPopularityFear() {
        int result = 2 * fearRate;
        for (Building building : buildings) {
            Location location = building.getLocation();
            Location castleLocation = new Location(0, 0);
            int d = 20;
            List<String> friendlyBuildings = List.of("Church", "Hovel", "Cathedral");
            List<String> fearfulBuildings = List.of("Barracks", "MercenaryPost");
            if (Math.abs(location.x - castleLocation.x) + Math.abs(location.y - castleLocation.y) > d) {
                result += (friendlyBuildings.contains(building.getName())) ? +1 : 0;
                result += (fearfulBuildings.contains(building.getName())) ? -1 : 0;
            }
        }
        return result;
    }

    public void updatePopularity() {
        // TODO remember to update popularity every turn
        this.popularity += calcPopularityFear();
        this.popularity += calcPopularityFood();
        this.popularity += calcPopularityReligion();
        this.popularity += calcPopularityTax();
    }
}