package controller.view_controllers;

import controller.controller.CoreTradeMenuController;
import model.User;
import view.TradeMenu;

import java.util.regex.Matcher;

public class TradeMenuController {
    private CoreTradeMenuController coreTradeMenuController;

    public TradeMenuController(User loggedInUser , TradeMenu tradeMenu) {
        this.coreTradeMenuController = new CoreTradeMenuController(loggedInUser , tradeMenu);
    }

    public String showTradeList(){
        return null;
    }

    public String showTradeHistory(){
        return null;
    }

    public String acceptTrade(Matcher matcher){
        return null;
    }

    public String tradeRequest(Matcher matcher){
        return null;
    }
}
