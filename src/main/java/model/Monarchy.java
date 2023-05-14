package model;

import model.building.Building;
import model.building.ProductionBuilding;
import model.building.Storage;
import model.man.Man;

import java.util.*;

public class Monarchy {
    private final List<Building> buildings = new ArrayList<>();
    private final List<GoodsType> foodTypes = new ArrayList<>();
    private final List<Man> men = new ArrayList<>();
    private final Storage[] storages = new Storage[3];
    private final TradingSystem tradingSystem;
    private final User king;
    private final MonarchyColorType color;
    private int popularity, taxRate, foodRate, gold, fearRate, religiousBuildingCount;
    private int population = 20;
    private Man lord;

    public Monarchy(User king, MonarchyColorType color, GameMap gameMap, Location mainKeepLocation) {
        storages[0] = new Storage(GoodsType.getGranaryGoods(), 30000, king, gameMap.getCell(mainKeepLocation.getVicintyLocation(1, 0)), "Granary");
        storages[1] = new Storage(GoodsType.getStockPileGoods(), 30000, king, gameMap.getCell(mainKeepLocation.getVicintyLocation(-1, 0)), "Stockpile");
        storages[2] = new Storage(GoodsType.getArmouryGoods(), 30000, king, gameMap.getCell(mainKeepLocation.getVicintyLocation(1, 1)), "Armoury");
        gameMap.getCell(mainKeepLocation.getVicintyLocation(1, 0)).setBuilding(storages[0]);
        gameMap.getCell(mainKeepLocation.getVicintyLocation(-1, 0)).setBuilding(storages[1]);
        gameMap.getCell(mainKeepLocation.getVicintyLocation(1, 1)).setBuilding(storages[2]);
        this.king = king;
        this.tradingSystem = new TradingSystem(king);
        this.popularity = 50;
        this.taxRate = 0;
        this.foodRate = 0;
        this.gold = 0;
        this.fearRate = 0;
        this.color = color;
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

    public int getPopulation() {
        return population;
    }

    public void changeGold(int amount) {
        gold += amount;
    }

    public void addAllMen(Man[] menToBeAdded) {
        men.addAll(List.of(menToBeAdded));
    }

    public void addMan(Man man) {
        men.add(man);
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

    public List<Man> getMen() {
        return men;
    }

    public List<Building> getBuildings() {
        return buildings;
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

    public void run() {
        buildings.stream().filter(building -> building instanceof ProductionBuilding)
                .forEach(building -> ((ProductionBuilding) building).act());
        feedPopulation();
        getTax();
        this.popularity += calcPopularityFear();
        this.popularity += calcPopularityReligion();
    }

    public void changePopularity(int amount) {
        this.popularity += amount;
    }

    private int foodCount() {
        int totalFood = 0;
        totalFood += this.getGranary().getGoodsCount(GoodsType.BREAD);
        totalFood += this.getGranary().getGoodsCount(GoodsType.MEAT);
        totalFood += this.getGranary().getGoodsCount(GoodsType.APPLE);
        totalFood += this.getGranary().getGoodsCount(GoodsType.CHEESE);
        return totalFood;
    }

    private int getFoodDiversity() {
        int foodDiversity = 0;
        if (this.getGranary().getGoodsCount(GoodsType.BREAD) > 0) {
            foodDiversity++;
        }
        if (this.getGranary().getGoodsCount(GoodsType.MEAT) > 0) {
            foodDiversity++;
        }
        if (this.getGranary().getGoodsCount(GoodsType.APPLE) > 0) {
            foodDiversity++;
        }
        if (this.getGranary().getGoodsCount(GoodsType.CHEESE) > 0) {
            foodDiversity++;
        }
        return foodDiversity;
    }

    private void feedPopulation() {
        int totalFood = foodCount();
        double maxRate = (double) totalFood / ((double) this.getPopulation() * 0.5);
        if (this.getPopulation() == 0) {
            maxRate = 4;
        }
        maxRate -= 2;
        if (foodRate > maxRate) {
            foodRate = (int) maxRate;
        }
        this.popularity += getFoodDiversity();
        this.popularity += calcPopularityFood();
        double foodNeeded = this.getPopulation() * ((foodRate + 2) * 0.5);
        List<GoodsType> foods = Arrays.stream(GoodsType.getGranaryGoods())
                .sorted(Comparator.comparingInt(this::getGood)).toList();
        for (int i = 0; i < foods.size(); i++) {
            GoodsType food = foods.get(i);
            int foodAmount = getGood(food);
            double requiredFood = foodNeeded / (foods.size() - i);
            requiredFood = Math.floor(requiredFood * 2) / 2;
            if (foodAmount > requiredFood) {
                foodNeeded -= requiredFood;
                putGood(food, (int) (foodAmount - requiredFood));
            } else {
                foodNeeded -= foodAmount;
                putGood(food, 0);
            }
        }
    }

    private void getTax() {
        int totalTax = 0;
        if (taxRate < 0) {
            totalTax += (taxRate * (0.2) - 0.4) * this.getPopulation();
        }
        if (taxRate > 0) {
            totalTax += (taxRate * (0.2) + 0.4) * this.getPopulation();
        }
        this.changeGold(totalTax);
        this.popularity += calcPopularityTax();
    }

    public boolean isDead() {
        return this.lord.getHitpoint() <= 0;
    }

    public void setLord(Man lord) {
        this.lord = lord;
    }
}