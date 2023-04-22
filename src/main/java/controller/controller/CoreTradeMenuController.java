package controller.controller;

import controller.view_controllers.TradeMenuController;
import model.Goods;
import model.StrongholdCrusader;
import model.User;
import view.TradeMenu;

import java.util.Scanner;

public class CoreTradeMenuController {
    private User loggedInUser;

    private final TradeMenu tradeMenu;

    public CoreTradeMenuController(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        tradeMenu = new TradeMenu(new TradeMenuController(StrongholdCrusader.getCurrentUser()));
    }

    public void run(Scanner scanner){
        tradeMenu.run(scanner);
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
