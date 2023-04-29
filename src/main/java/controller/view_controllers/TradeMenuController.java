package controller.view_controllers;

import controller.controller.CoreTradeMenuController;
import model.GoodsType;
import model.User;
import org.apache.commons.lang3.math.NumberUtils;
import view.TradeMenu;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenuController {
    private final CoreTradeMenuController coreController;
    private final TradeMenu menu;
    private User loggedInUser;

    public TradeMenuController(CoreTradeMenuController coreController, User loggedInUser, Scanner scanner) {
        this.menu = new TradeMenu(this, scanner);
        this.coreController = coreController;
        this.loggedInUser = loggedInUser;
    }

    public TradeMenu getMenu() {
        return menu;
    }

    public String showTradeList(){
        return coreController.showTradeList();
    }

    public String showTradeHistory(){
        return coreController.showTradeHistory();
    }

    public String acceptTrade(Matcher matcher){
        Map<String, String> args = Utilities.extractOptionsFromString(
                matcher.group("options"));
        if (!args.containsKey("-i") || !args.containsKey("-m")) {
            return "please enter all arguments";
        }
        return coreController.acceptTrade(args.get("-i"), args.get("-m"));
    }

    public String tradeRequest(Matcher matcher){
        Map<String, String> args = Utilities.extractOptionsFromString(
                matcher.group("options"));
        if (!args.containsKey("-t") || !args.containsKey("-a") ||
                !args.containsKey("-p") || !args.containsKey("-m") ||
                !args.containsKey("-u")) {
            return "please enter all arguments";
        }
        String goodsType = args.get("-t");
        String goodsAmount = args.get("-a");
        String price = args.get("-p");
        String massage = args.get("-m");
        String otherUserName = args.get("-u");
        return coreController.tradeRequest(otherUserName, goodsType, goodsAmount, price, massage);
    }
}
