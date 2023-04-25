package controller.view_controllers;

import controller.controller.CoreTradeMenuController;
import model.User;

import java.util.regex.Matcher;

public class TradeMenuController {
    private final CoreTradeMenuController coreController;
    private User loggedInUser;

    public TradeMenuController(CoreTradeMenuController coreController, User loggedInUser) {
        this.coreController = coreController;
        this.loggedInUser = loggedInUser;
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
