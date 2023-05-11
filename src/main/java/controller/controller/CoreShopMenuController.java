package controller.controller;

import controller.view_controllers.ShopMenuController;
import model.GoodsType;
import model.Match;
import model.Monarchy;
import model.StrongholdCrusader;
import view.ShopMenu;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class CoreShopMenuController {
    private final Scanner scanner;
    private final ShopMenu shopMenu;
    private final ShopMenuController shopMenuController;
    private Match match;

    public CoreShopMenuController(Match match, Scanner scanner) {
        this.scanner = scanner;
        this.shopMenuController = new ShopMenuController(this , scanner);
        this.shopMenu = shopMenuController.getMenu();
        this.match = match;
    }

    public ShopMenuController getShopMenuController() {
        return shopMenuController;
    }

    public String run() {
        String shopMenuResult;
        while (true) {
            shopMenuResult = shopMenu.run();
            if (shopMenuResult.equals("Exit")) return "Exit";
        }
    }

    public String showPriceList() {
        StringBuilder builder = new StringBuilder();
        Map <GoodsType , Integer> storage = match.getCurrentMonarchy().getStorage();
        for (GoodsType type : GoodsType.values()) {
            builder.append(type.getName()).append(" => ");
            builder.append("sell price: ").append(type.getShopSellPrice());
            builder.append(" | buy price: ").append(type.getShopBuyPrice());
            builder.append(" | amount: ").append(storage.getOrDefault(type, 0));
            builder.append("\n");
        }
        return builder.toString();
    }

    public String buy(GoodsType goodsType , int amount) {
        int priceBuy = goodsType.getShopBuyPrice();
        int storageLimit = goodsType.getStorageLimit();
        Monarchy monarchy = match.getCurrentMonarchy();
        Map<GoodsType, Integer> storage = getTypeMap(goodsType, monarchy);
        if (storage.containsKey(goodsType) && storage.get(goodsType) + amount > storageLimit)
            return "You do not have enough space!";
        if (!storage.containsKey(goodsType) && amount > storageLimit)
            return "You do not have enough space!";
        if (monarchy.getGold() < amount * priceBuy)
            return "You do not have enough gold!";
        storage.put(goodsType , storage.get(goodsType) + amount);
        monarchy.changeGold(-amount * priceBuy);
        return "Buy successful!";
    }

    public String sell(GoodsType goodsType , int amount) {
        int priceSell = goodsType.getShopSellPrice();
        Monarchy monarchy = match.getCurrentMonarchy();
        Map<GoodsType, Integer> storage = getTypeMap(goodsType, monarchy);
        if (!storage.containsKey(goodsType)) return "You do not have this item in your storage!";
        if (storage.containsKey(goodsType) && storage.get(goodsType) < amount)
            return "You do not have enough item to sell!";
        storage.put(goodsType , storage.get(goodsType) - amount);
        monarchy.changeGold(amount * priceSell);
        return "Sell successful!";
    }

    private static Map<GoodsType, Integer> getTypeMap(GoodsType goodsType, Monarchy monarchy) {
        Map <GoodsType , Integer> storage;
        if (Arrays.stream(GoodsType.getGranaryGoods()).anyMatch(goodsType1 -> goodsType1 == goodsType))
            storage = monarchy.getGranary().getMap();
        else if (Arrays.stream(GoodsType.getArmouryGoods()).anyMatch(goodsType1 -> goodsType1 == goodsType)) {
            storage = monarchy.getArmoury().getMap();
        } else {
            storage = monarchy.getStockPile().getMap();
        }
        return storage;
    }
}