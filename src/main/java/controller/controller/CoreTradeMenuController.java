package controller.controller;

import model.Goods;
import model.User;
import view.TradeMenu;

public class CoreTradeMenuController {
    private User loggedInUser;

    private TradeMenu tradeMenu;

    public CoreTradeMenuController(User loggedInUser, TradeMenu tradeMenu) {
        this.loggedInUser = loggedInUser;
        this.tradeMenu = tradeMenu;
    }

    public String showTradeList(){
        return null;
    }

    public String showTradeHistory(){
        return null;
    }

    public String acceptTrade(int id , String massage){
        return null;
    }

    public String tradeRequest(Goods goods , int goodsAmount , int price , String massage){
        return null;
    }
}
