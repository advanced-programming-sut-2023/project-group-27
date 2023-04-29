package controller.controller;

import controller.view_controllers.TradeMenuController;
import model.*;
import org.apache.commons.lang3.math.NumberUtils;
import view.TradeMenu;

import java.util.Scanner;

public class CoreTradeMenuController {
    private final Scanner scanner;
    private User loggedInUser;
    private TradingSystem tradingSystem;
    private final TradeMenuController controller;
    private final TradeMenu menu;

    public CoreTradeMenuController(User loggedInUser , Scanner scanner) {
        this.scanner = scanner;
        this.loggedInUser = loggedInUser;
        if (loggedInUser != null) {
            this.tradingSystem = loggedInUser.getMonarchy().getTradingSystem();
        }
        this.controller = new TradeMenuController(this, loggedInUser, scanner);
        this.menu = this.controller.getMenu();
    }

    public void run(){
        menu.showInformation(tradingSystem.notifications());
        menu.run();
    }

    public String showTradeList(){
        return tradingSystem.showAwaitingTrades();
    }

    public String showTradeHistory(){
        return tradingSystem.showHistory();
    }

    public String acceptTrade(String id , String massage){
        Trade trade = null;
        if (!NumberUtils.isNumber(id)) {
            return "id must be numeric\n";
        }
        int idInt = Integer.parseInt(id);
        return tradingSystem.acceptTrade(idInt);
    }

    public String tradeRequest(String otherUserName, String goods , String goodsAmount , String price , String massage){
        User otherUser = StrongholdCrusader.getUserByName(otherUserName);
        if (otherUser == null) {
            return "no such user\n";
        }
        GoodsType type = null;
        for (GoodsType goodsType1 : GoodsType.getGoods()) {
            if (goodsType1.getName().equals(goods)) {
                type = goodsType1;
                break;
            }
        }
        if (type == null) {
            return "no such goods type\n";
        }
        if (!NumberUtils.isNumber(goodsAmount) || !NumberUtils.isNumber(price)) {
            return "goods amount and price must be numeric\n";
        }
        int goodsAmountInt = Integer.parseInt(goodsAmount);
        int priceInt = Integer.parseInt(price);
        if (priceInt < 0) {
            return "price must be non-negative\n";
        }
        if (goodsAmountInt <= 0) {
            return "goods amount must be positive\n";
        }
        TradingSystem tradingSystem = otherUser.getMonarchy().getTradingSystem();
        tradingSystem.addTrade(loggedInUser, type, goodsAmountInt, priceInt, massage);
        return null;
    }
}
