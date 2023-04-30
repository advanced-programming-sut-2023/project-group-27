package controller.view_controllers;

import controller.controller.CoreLoginMenuController;
import controller.controller.CoreShopMenuController;
import view.ShopMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenuController {
    private final ShopMenu menu;
    private final CoreShopMenuController coreController;

    public ShopMenuController(CoreShopMenuController coreController , Scanner scanner) {
        this.menu = new ShopMenu(this , scanner);
        this.coreController = coreController;
    }

    public ShopMenu getMenu() {
        return menu;
    }

    public String buy(Matcher matcher) {
        return null;
    }
}
