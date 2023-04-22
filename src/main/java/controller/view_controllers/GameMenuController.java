package controller.view_controllers;

import controller.controller.CoreGameMenuController;
import model.User;

import java.util.regex.Matcher;

public class GameMenuController {
    private CoreGameMenuController coreGameMenuController;

    public GameMenuController(User loggedInUser) {
        this.coreGameMenuController = new CoreGameMenuController(loggedInUser);
    }

    public String showPopularityFactors(){
        return null;
    }

    public int showPopularity(){
        return 0;
    }

    public String showFoodList(){
        return null;
    }

    public String setFoodRate(Matcher matcher){
        return null;
    }

    public String showFoodRate(){
        return null;
    }

    public String setTaxRate(Matcher matcher){
        return null;
    }

    public String showTaxRate(){
        return null;
    }

    public String setFearRate(Matcher matcher){
        return null;
    }

    public String showFearRate(){
        return null;
    }

    public String dropBuilding(Matcher matcher){
        return null;
    }

    public String selectBuilding(Matcher matcher){
        return null;
    }

    public String selectUnit(Matcher matcher){
        return null;
    }
}
