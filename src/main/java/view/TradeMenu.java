package view;

import controller.view_controllers.TradeMenuController;
import view.enums.TradeMenuRegexes;

import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

public class TradeMenu extends MenuBase{
    private final TradeMenuController controller;

    public TradeMenu(TradeMenuController controller, Scanner scanner) {
        super(scanner);
        this.controller = controller;
    }

    public String run() {
        System.out.println("Entered Trade Menu");
        while (true) {
            String command = scanner.nextLine();
            String output = "";

            if (TradeMenuRegexes.CREATE_TRADE.getMatcher(command).matches()) {
                output = controller.tradeRequest(
                        TradeMenuRegexes.CREATE_TRADE.getMatcher(command));
                continue;
            }

            if (TradeMenuRegexes.TRADE_LIST.getMatcher(command).matches()) {
                output = controller.showTradeList();
                continue;
            }

            if (TradeMenuRegexes.TRADE_HISTORY.getMatcher(command).matches()) {
                output = controller.showTradeHistory();
                continue;
            }

            if (TradeMenuRegexes.TRADE_ACCEPT.getMatcher(command).matches()) {
                output = controller.acceptTrade(
                        TradeMenuRegexes.TRADE_ACCEPT.getMatcher(command));
                continue;
            }

            if (command.equals("Exit")) {
                break;
            }

            if (output.equals("")) {
                output = "Invalid command";
            }
            System.out.println(output);
        }
        return null;
    }
}
