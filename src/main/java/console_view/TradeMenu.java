package console_view;

import controller.view_controllers.TradeMenuController;
import console_view.enums.TradeMenuRegexes;

import java.util.Scanner;

public class TradeMenu extends MenuBase{
    private final TradeMenuController controller;

    public TradeMenu(TradeMenuController controller, Scanner scanner) {
        super(scanner);
        this.controller = controller;
    }

    public String run() {
        System.out.println("Entered Trade Menu");
        System.out.println("your options are:" +
                "\n1. trade -t [resourceType] -a [resourceAmount] -p [price] -m [message] -u [user]" +
                "\n2. trade list" +
                "\n3. trade history" +
                "\n4. trade accept -i [id] -m message" +
                "\n5. Exit");
        while (true) {
            String command = scanner.nextLine();
            String output;

            if (TradeMenuRegexes.CREATE_TRADE.getMatcher(command).matches()) {
                output = controller.tradeRequest(
                        TradeMenuRegexes.CREATE_TRADE.getMatcher(command));
                System.out.print(output);
                continue;
            }

            if (TradeMenuRegexes.TRADE_LIST.getMatcher(command).matches()) {
                output = controller.showTradeList();
                System.out.print(output);
                continue;
            }

            if (TradeMenuRegexes.TRADE_HISTORY.getMatcher(command).matches()) {
                output = controller.showTradeHistory();
                System.out.print(output);
                continue;
            }

            if (TradeMenuRegexes.TRADE_ACCEPT.getMatcher(command).matches()) {
                output = controller.acceptTrade(
                        TradeMenuRegexes.TRADE_ACCEPT.getMatcher(command));
                System.out.print(output);
                continue;
            }

            if (command.equals("Exit")) {
                break;
            }
            System.out.println("Invalid command!");
        }
        return null;
    }
}
