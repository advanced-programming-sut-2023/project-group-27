package controller.controller;

import controller.view_controllers.ShopMenuController;
import model.GoodsType;
import view.ShopMenu;

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
        return null;
    }

    public String sell(GoodsType goodsType , int amount) {
        return null;
    }

}
