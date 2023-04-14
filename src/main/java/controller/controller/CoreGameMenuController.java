package controller.controller;

import model.User;
import view.GameMenu;

public class CoreGameMenuController {
    private User loggedInUser;
    private GameMenu gameMenu;

    public CoreGameMenuController(User loggedInUser, GameMenu gameMenu) {
        this.loggedInUser = loggedInUser;
        this.gameMenu = gameMenu;
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

    public String setFoodRate(int rate){
        return null;
    }

    public String showFoodRate(){
        return null;
    }

    public String setTaxRate(int rate){
        return null;
    }

    public String showTaxRate(){
        return null;
    }

    public String setFearRate(int rate){
        return null;
    }

    public String showFearRate(){
        return null;
    }

    public String dropBuilding(int x , int y , String type){
        return null;
    }

    public String selectBuilding(int x , int y){
        return null;
    }

    public String selectUnit(int x , int y){
        return null;
    }

}
