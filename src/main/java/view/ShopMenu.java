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
        System.out.print("Entered Shop Menu\n");
        System.out.print("your options are:\n" +
                "1. show price list\n" +
                "2. buy -i <item’s name> -a <item’s amount>\n" +
                "3. sell -i <item’s name> -a <item’s amount>\n" +
                "4. Exit\n");
        String command , output;
        while (true) {
            command = scanner.nextLine();
            if (ShopMenuRegexes.EXIT.getMatcher(command).matches()) return "EXit";
            else if (ShopMenuRegexes.SHOWPRICELIST.getMatcher(command).matches()) {
                output = controller.showPriceList();
                System.out.print(output);
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
