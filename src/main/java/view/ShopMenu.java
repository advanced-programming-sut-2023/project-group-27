package view;

import controller.view_controllers.ShopMenuController;
import view.enums.ShopMenuRegexes;

import java.util.Scanner;

public class ShopMenu {
    private final ShopMenuController controller;
    private final Scanner scanner;

    public ShopMenu(ShopMenuController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public String run() {
        String command , output;
        while (true) {
            command = scanner.nextLine();
            if (ShopMenuRegexes.EXIT.getMatcher(command).matches()) return "EXit";
            else if (ShopMenuRegexes.SHOWPRICELIST.getMatcher(command).matches()) {
                output = controller.showPriceList();
                System.out.println(output);
            }
            else if (ShopMenuRegexes.BUY.getMatcher(command).matches()) {
                output = controller.buy(ShopMenuRegexes.BUY.getMatcher(command));
                System.out.println(output);
            }
            else if (ShopMenuRegexes.SELL.getMatcher(command).matches()) {
                output = controller.sell(ShopMenuRegexes.SELL.getMatcher(command));
                System.out.println(output);
            }
            else System.out.println("Invalid command!");
        }
    }
}
