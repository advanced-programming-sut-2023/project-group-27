package controller.view_controllers;

import controller.controller.CoreShopMenuController;
import model.GoodsType;
import console_view.ShopMenu;

import java.util.Map;
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

    public String showPriceList() {
        return coreController.showPriceList();
    }

    public String buy(Matcher matcher) {
        matcher.matches();
        String argsString = matcher.group("options");
        Map <String , String> args = Utilities.extractOptionsFromString(argsString);
        if (args == null) return "Dont determine a field twice!";
        if (!args.containsKey("i")) return "Determine Item's name";
        if (!args.containsKey("a")) return "Determine Item's amount";
        int amount;
        try {
            amount = Integer.parseInt(args.get("a"));
        } catch (NumberFormatException e) {
            return "Invalid amount format!";
        }
        if (amount <= 0) return "Invalid amount number!";
        String itemName = args.get("i");
        GoodsType goodsType = GoodsType.getTypeByName(itemName);
        return coreController.buy(goodsType , amount);
    }

    public String sell(Matcher matcher) {
        matcher.matches();
        String argsString = matcher.group("options");
        Map <String , String> args = Utilities.extractOptionsFromString(argsString);
        if (args == null) return "Dont determine a field twice!";
        if (!args.containsKey("i")) return "Determine Item's name";
        if (!args.containsKey("a")) return "Determine Item's amount";
        int amount;
        try {
            amount = Integer.parseInt(args.get("a"));
        } catch (NumberFormatException e) {
            return "Invalid amount format!";
        }
        if (amount <= 0) return "Invalid amount number!";
        String itemName = args.get("i");
        GoodsType goodsType = GoodsType.getTypeByName(itemName);
        return coreController.sell(goodsType , amount);
    }
}
