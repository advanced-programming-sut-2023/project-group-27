package controller.controller;

import model.Match;
import server.Connection;

public class CoreGameOnline {
    private Connection connection;
    private CoreGameMenuController controller;

    public CoreGameOnline(Match currentMatch, Connection connection) {
        this.connection = connection;
        controller = new CoreGameMenuController(currentMatch, null);
    }

    public String setFoodRate(String rateStr){
        return controller.setFoodRate(rateStr);
    }

    public String setTaxRate(String rateStr){
        return controller.setTaxRate(rateStr);
    }

    public String setFearRate(String rateStr){
        return controller.setFearRate(rateStr);
    }

    public String dropBuilding(String xStr , String yStr , String type){
        return controller.dropBuilding(xStr, yStr, type);
    }

    public String nextTurn() {
        return controller.nextTurn();
    }
}
