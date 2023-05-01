package controller.controller;

import controller.view_controllers.ShopMenuController;
import model.GoodsType;
import model.Monarchy;
import model.StrongholdCrusader;
import model.building.Storage;
import view.ShopMenu;

import java.util.Map;
import java.util.Scanner;

public class CoreShopMenuController {
    private final Scanner scanner;
    private final ShopMenu shopMenu;
    private final ShopMenuController shopMenuController;

    public CoreShopMenuController(Scanner scanner) {
        this.scanner = scanner;
        this.shopMenuController = new ShopMenuController(this , scanner);
        shopMenu = shopMenuController.getMenu();
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
        return null;
    }

    public String buy(GoodsType goodsType , int amount) {
        int priceBuy = goodsType.getShopBuyPrice();
        int storageLimit = goodsType.getStorageLimit();
        Monarchy monarchy = StrongholdCrusader.getCurrentUser().getMonarchy();
        Map <GoodsType , Integer> storage = StrongholdCrusader.getCurrentUser().getMonarchy().getStorage();
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
        Monarchy monarchy = StrongholdCrusader.getCurrentUser().getMonarchy();
        Map <GoodsType , Integer> storage = StrongholdCrusader.getCurrentUser().getMonarchy().getStorage();
        if (!storage.containsKey(goodsType)) return "You do not have this item in your storage!";
        if (storage.containsKey(goodsType) && storage.get(goodsType) < amount)
            return "You do not have enough item to sell!";
        storage.put(goodsType , storage.get(goodsType) - amount);
        monarchy.changeGold(amount * priceSell);
        return "Sell successful!";
    }

}
