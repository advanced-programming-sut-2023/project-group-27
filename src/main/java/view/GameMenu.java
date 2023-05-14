package view;

import controller.view_controllers.GameMenuController;
import view.enums.GameMenuRegexes;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private final GameMenuController controller;

    public GameMenu(GameMenuController controller) {
        this.controller = controller;
    }

    public String run(Scanner scanner) {
        System.out.println("Entered Game Menu");
        System.out.print("your options are:\n" +
                "1. show popularity factors\n" +
                "2. show popularity\n" +
                "3. show food list\n" +
                "4. food rate show\n" +
                "5. fear rate show\n" +
                "6. tax rate show\n" +
                "7. fear rate -r <rate>\n" +
                "8. food rate -r <rate>\n" +
                "9. tax rate -r <rate>\n" +
                "10. show map -x <x> -y <y>\n" +
                "11. drop building -x <x> -y <y> -t <type>\n" +
                "12. select building -x <x> -y <y>\n" +
                "13. select unit -x <x> -y <y> -t <type>\n" +
                "14. enter shop\n" +
                "15. enter map edit\n" +
                "16. show current player\n" +
                "17. enter trade menu\n" +
                "18. show monarchy\n" +
                "19. next turn\n" +
                "20. back\n");
        while (true) {
            String command = scanner.nextLine();
            String output;
            Matcher matcher;
            if (GameMenuRegexes.SHOW_POPULARITY_FACTORS.getMatcher(command).matches()) {
                System.out.print(controller.showPopularityFactors());
                continue;
            }
            if (GameMenuRegexes.SHOW_POPULARITY.getMatcher(command).matches()) {
                System.out.print(controller.showPopularity());
                continue;
            }
            if (GameMenuRegexes.SHOW_FOOD_LIST.getMatcher(command).matches()) {
                System.out.print(controller.showFoodList());
                continue;
            }
            if (GameMenuRegexes.SHOW_FOOD_RATE.getMatcher(command).matches()) {
                System.out.print(controller.showFearRate());
                continue;
            }
            if (GameMenuRegexes.SHOW_FEAR_RATE.getMatcher(command).matches()) {
                System.out.print(controller.showFearRate());
                continue;
            }
            if (GameMenuRegexes.SHOW_TAX_RATE.getMatcher(command).matches()) {
                System.out.print(controller.showTaxRate());
                continue;
            }
            if (GameMenuRegexes.SET_FEAR_RATE.getMatcher(command).matches()) {
                output = controller.setFearRate(
                        GameMenuRegexes.SET_FEAR_RATE.getMatcher(command));
                System.out.print(output);
                continue;
            }
            if (GameMenuRegexes.SET_FOOD_RATE.getMatcher(command).matches()) {
                output = controller.setFoodRate(
                        GameMenuRegexes.SET_FOOD_RATE.getMatcher(command));
                System.out.print(output);
                continue;
            }
            if (GameMenuRegexes.SET_TAX_RATE.getMatcher(command).matches()) {
                output = controller.setTaxRate(
                        GameMenuRegexes.SET_TAX_RATE.getMatcher(command));
                System.out.print(output);
                continue;
            }
            if (GameMenuRegexes.SELECT_BUILDING.getMatcher(command).matches()) {
                output = controller.selectBuilding(
                        GameMenuRegexes.SELECT_BUILDING.getMatcher(command));
                if (output != null) {
                    System.out.print(output);
                }
                continue;
            }
            if (GameMenuRegexes.SELECT_UNIT.getMatcher(command).matches()) {
                output = controller.selectUnit(
                        GameMenuRegexes.SELECT_UNIT.getMatcher(command));
                if (output != null)
                    System.out.print(output);
                continue;
            }
            if (GameMenuRegexes.SHOW_MAP.getMatcher(command).matches()) {
                output = controller.showMap(GameMenuRegexes.SHOW_MAP.getMatcher(command));
                if (output != null) {
                    System.out.print(output);
                }
                continue;
            }
            if (GameMenuRegexes.ENTER_SHOP.getMatcher(command).matches()) {
                controller.enterShop();
                continue;
            }
            if (GameMenuRegexes.ENTER_MAP_EDIT.getMatcher(command).matches()) {
                controller.enterMapEdit();
                continue;
            }
            if (GameMenuRegexes.SHOW_CURRENT_PLAYER.getMatcher(command).matches()) {
                System.out.println(controller.showCurrentPlayer());
                continue;
            }
            if ((matcher = GameMenuRegexes.DROP_BUILDING.getMatcher(command)).matches()) {
                System.out.print(controller.dropBuilding(matcher));
                continue;
            }
            if (GameMenuRegexes.ENTER_TRADE_MENU.getMatcher(command).matches()) {
                controller.enterTradeMenu();
                continue;
            }
            if (GameMenuRegexes.NEXT_TURN.getMatcher(command).matches()) {
                output = controller.nextTurn();
                System.out.println(output);
                if (output.equals("Game Over")) {
                    return "Exit";
                }
                continue;
            }
            if (GameMenuRegexes.SHOW_MONARCHY.getMatcher(command).matches()) {
                System.out.println(controller.showMonarchy());
                continue;
            }
            if (command.equals("Exit")) {
                return "Exit";
            }
            System.out.println("Invalid command!");
        }
    }
}