package view;

import controller.view_controllers.GameMenuController;
import view.enums.GameMenuRegexes;

import java.util.Scanner;

public class GameMenu {
    private final GameMenuController controller;

    public GameMenu(GameMenuController controller) {
        this.controller = controller;
    }

    public String run(Scanner scanner) {
        System.out.println("Entered Game Menu");
        while (true) {
            String command = scanner.nextLine();
            String output;
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

            }
            if (GameMenuRegexes.SELECT_UNIT.getMatcher(command).matches()) {

            }
            if (GameMenuRegexes.SHOW_MAP.getMatcher(command).matches()) {

            }
            if (command.equals("Exit")) {
                break;
            }
        }
        return null;
    }
}
