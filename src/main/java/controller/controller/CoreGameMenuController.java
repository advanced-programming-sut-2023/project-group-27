package controller.controller;

import controller.view_controllers.GameMenuController;
import model.Monarchy;
import model.StrongholdCrusader;
import model.building.Building;
import model.man.Man;
import view.GameMenu;

import java.util.Scanner;

public class CoreGameMenuController {
    private final Scanner scanner;
    private Monarchy currentMonarchy;
    private final GameMenu gameMenu;
    private final CoreTradeMenuController coreTradeMenuController;
    private CoreSelectUnitMenuController coreSelectUnitMenuController;
    private CoreSelectBuildingMenuController coreSelectBuildingMenuController;
    private CoreMapNavigationMenuController coreMapNavigationMenuController;

    public CoreGameMenuController(Monarchy currentMonarchy, Scanner scanner) {
        this.scanner = scanner;
        this.currentMonarchy = currentMonarchy;
        gameMenu = new GameMenu(new GameMenuController(this , StrongholdCrusader.getCurrentUser()));
        coreTradeMenuController = new CoreTradeMenuController(StrongholdCrusader.getCurrentUser() , scanner);
    }

    public String run(){
        String gameMenuResult;
        while (true){
            gameMenuResult = gameMenu.run(scanner);
            switch (gameMenuResult){
                case "Match end":
                    return "Match end";
                case "Enter trade manu":
                    coreTradeMenuController.run();
                    break;
            }
        }
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

    public String selectBuilding(int x , int y) {
        Building selectedBuilding = null;
        coreSelectBuildingMenuController = new CoreSelectBuildingMenuController(selectedBuilding , scanner, currentMonarchy);
        coreSelectBuildingMenuController.run();
        return null;
    }

    public String selectUnit(int x , int y) {
        Man selectedMan = null;
        coreSelectUnitMenuController = new CoreSelectUnitMenuController(selectedMan , scanner);
        coreSelectUnitMenuController.run();
        return null;
    }

    public String showMap(int x, int y) {
        coreMapNavigationMenuController  = new CoreMapNavigationMenuController(x, y, scanner,
                StrongholdCrusader.getCurrentMap(),
                this);
        coreMapNavigationMenuController.run();
        return null;
    }

}
