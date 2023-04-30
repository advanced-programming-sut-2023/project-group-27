package controller.controller;

import controller.view_controllers.ShopMenuController;
import view.ShopMenu;

import java.util.Scanner;

public class CoreShopMenuController {
    private final Scanner scanner;
    private final ShopMenu shopMenu;
    private final ShopMenuController shopMenuController;
    private final CoreShopMenuController coreShopMenuController;

    public CoreShopMenuController(Scanner scanner) {
        this.scanner = scanner;
        this.shopMenuController = new ShopMenuController(this , scanner);
        shopMenu = shopMenuController.getMenu();
        coreShopMenuController = new CoreShopMenuController(scanner);
    }

    public String run() {
        return null;
    }
}
