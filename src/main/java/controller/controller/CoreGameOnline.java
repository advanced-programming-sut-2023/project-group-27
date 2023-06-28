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
        connection.send("food rate -r " + rateStr);
        return controller.setFoodRate(rateStr);
    }

    public String setTaxRate(String rateStr){
        connection.send("tax rate -r " + rateStr);
        return controller.setTaxRate(rateStr);
    }

    public String setFearRate(String rateStr){
        connection.send("fear rate -r " + rateStr);
        return controller.setFearRate(rateStr);
    }

    public String dropBuilding(String xStr , String yStr , String type){
        connection.send("drop building -x " + xStr + " -y " + yStr + " -t " + type);
        return controller.dropBuilding(xStr, yStr, type);
    }

    public String nextTurn() {
        return controller.nextTurn();
    }
}
