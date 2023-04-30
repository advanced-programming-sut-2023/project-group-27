package controller.controller;

import controller.view_controllers.GameMenuController;
import model.GameMap;
import model.StrongholdCrusader;
import model.User;
import model.building.Building;
import model.man.Man;
import org.apache.commons.lang3.math.NumberUtils;
import view.GameMenu;

import java.util.Scanner;

public class CoreGameMenuController {
    private final Scanner scanner;
    private User loggedInUser;
    private final GameMap map;
    private final GameMenu gameMenu;
    private final CoreTradeMenuController coreTradeMenuController;
    private CoreSelectUnitMenuController coreUnitController;
    private CoreSelectBuildingMenuController coreBuildingController;

    public CoreGameMenuController(Scanner scanner) {
        this.scanner = scanner;
        this.loggedInUser = StrongholdCrusader.getCurrentUser();
        this.map = StrongholdCrusader.getCurrentMap();
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

    public String selectBuilding(String xStr , String yStr) {
        Integer x,y;
        if (!NumberUtils.isNumber(xStr) || ! NumberUtils.isNumber(yStr)) {
            return "x and y should be integers";
        }
        x = Integer.parseInt(xStr);
        y = Integer.parseInt(yStr);
        String coordinateError = XYCheck(x, y);
        if (coordinateError != null) return coordinateError;
        Building selectedBuilding = map.getCell(x, y).getBuilding();
        if (selectedBuilding == null) {
            return "no building here\n";
        }
        coreBuildingController =
                new CoreSelectBuildingMenuController(selectedBuilding , scanner);
        coreBuildingController.run();
        return null;
    }

    public String selectUnit(String xStr , String yStr) {
        Integer x,y;
        if (!NumberUtils.isNumber(xStr) || ! NumberUtils.isNumber(yStr)) {
            return "x and y should be integers";
        }
        x = Integer.parseInt(xStr);
        y = Integer.parseInt(yStr);
        String coordinateError = XYCheck(x, y);
        if (coordinateError != null) return coordinateError;
        Man selectedMan = map.getCell(x, y).getMan();
        coreUnitController = new CoreSelectUnitMenuController(selectedMan , scanner);
        coreUnitController.run();
        return null;
    }

    private String XYCheck(int x, int y) {
        if (x >= map.getWidth() || x < 0) {
            return "x is out of range it should be between 0 and " +
                    (map.getWidth() - 1) + "\n";
        }
        if (y >= map.getHeight() || y < 0) {
            return "y is out of range it should be between 0 and " +
                    (map.getHeight() - 1) + "\n";
        }
        return null;
    }

    public String showMap(int x, int y) {
        CoreMapNavigationMenuController coreNavigationController = new CoreMapNavigationMenuController(x, y, scanner,
                StrongholdCrusader.getCurrentMap(),
                this);
        coreNavigationController.run();
        return null;
    }

}
