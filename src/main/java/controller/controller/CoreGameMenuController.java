package controller.controller;

import controller.view_controllers.GameMenuController;
import model.StrongholdCrusader;
import model.User;
import view.GameMenu;

import java.util.Scanner;

public class CoreGameMenuController {
    private User loggedInUser;
    private final GameMenu gameMenu;

    public CoreGameMenuController(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        gameMenu = new GameMenu(new GameMenuController(StrongholdCrusader.getCurrentUser()));
    }

    public String run(Scanner scanner){
        return null;
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
