package controller.controller;

import controller.view_controllers.GameMenuController;
import model.*;
import model.building.Building;
import model.man.Man;
import org.apache.commons.lang3.math.NumberUtils;
import view.GameMenu;

import java.util.Scanner;

public class CoreGameMenuController {
    private final Scanner scanner;
    private Match currentMatch;
    private User currentUser;
    private Monarchy currentMonarchy;
    private final GameMap map;
    private final GameMenu gameMenu;
    private final CoreTradeMenuController coreTradeMenuController;
    private CoreSelectUnitMenuController coreUnitController;
    private CoreSelectBuildingMenuController coreBuildingController;
    private GameMenuController gameController;

    public CoreGameMenuController(Match currentMatch, Scanner scanner) {
        this.scanner = scanner;
        this.currentMatch = currentMatch;
        this.currentUser = StrongholdCrusader.getCurrentUser();
        this.map = currentMatch.getCurrentMatchMap();
        this.gameController = new GameMenuController(this, currentMatch);
        this.gameMenu = new GameMenu(this.gameController);
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
        // TODO implement here
        return null;
    }

    public String showPopularity(){
        return String.valueOf(currentUser.getMonarchy().calcPopularity());
    }

    public String showFoodList(){
        StringBuilder result = new StringBuilder();
        for (GoodsType food : GoodsType.getGranaryGoods()) {
            int num = currentUser.getMonarchy().getGranary().getGoodsCount(food);
            if (num > 0) {
                result.append(food.getName()).append(": ").append(num).append("\n");
            }
        }
        return result.toString();
    }

    public String setFoodRate(String rateStr){
        if (!NumberUtils.isNumber(rateStr)) return "rate should be an integer\n";
        int rate = Integer.parseInt(rateStr);
        if (rate < -2 || rate > 2) return "rate should be between -2 and 2\n";
        currentUser.getMonarchy().setFoodRate(rate);
        return "success\n";
    }

    public String showFoodRate(){
        return String.valueOf(currentUser.getMonarchy().getFoodRate());
    }

    public String setTaxRate(String rateStr){
        if (!NumberUtils.isNumber(rateStr)) return "rate should be an integer\n";
        int rate = Integer.parseInt(rateStr);
        if (rate < -3 || rate > 8) return "rate should be between -3 and 8\n";
        currentUser.getMonarchy().setTaxRate(rate);
        return "success\n";
    }

    public String showTaxRate(){
        return String.valueOf(currentUser.getMonarchy().getTaxRate());
    }

    public String setFearRate(String rateStr){
        if (!NumberUtils.isNumber(rateStr)) return "rate should be an integer\n";
        int rate = Integer.parseInt(rateStr);
        if (rate < -5 || rate > 5) return "rate should be between -5 and 5\n";
        currentUser.getMonarchy().setFearRate(rate);
        return "success\n";
    }

    public String showFearRate(){
        return String.valueOf(currentUser.getMonarchy().getFearRate());
    }

    public String dropBuilding(int x , int y , String type){
        // TODO implement here
        return null;
    }

    public String selectBuilding(String xStr , String yStr) {
        if (!NumberUtils.isNumber(xStr) || ! NumberUtils.isNumber(yStr)) {
            return "x and y should be integers";
        }
        int x = Integer.parseInt(xStr), y = Integer.parseInt(yStr);
        if (XYCheck(x, y) != null) return XYCheck(x, y);
        Building selectedBuilding = map.getCell(x, y).getBuilding();
        if (selectedBuilding == null) {
            return "no building here\n";
        }
        coreBuildingController =
                new CoreSelectBuildingMenuController(selectedBuilding , scanner, currentMonarchy);
        coreBuildingController.run();
        return null;
    }

    public String selectUnit(String xStr , String yStr) {
        if (!NumberUtils.isNumber(xStr) || ! NumberUtils.isNumber(yStr)) {
            return "x and y should be integers";
        }
        int x = Integer.parseInt(xStr), y = Integer.parseInt(yStr);
        if (XYCheck(x, y) != null) return XYCheck(x, y);
        Man selectedMan = map.getCell(x, y).getMan();
        coreUnitController = new CoreSelectUnitMenuController(selectedMan , scanner);
        coreUnitController.run();
        return null;
    }

    public String showMap(String xStr, String yStr) {
        if (!NumberUtils.isNumber(xStr) || ! NumberUtils.isNumber(yStr)) {
            return "x and y should be integers";
        }
        int x = Integer.parseInt(xStr), y = Integer.parseInt(yStr);
        if (XYCheck(x, y) != null) return XYCheck(x, y);
        CoreMapNavigationMenuController coreNavigationController =
                new CoreMapNavigationMenuController(
                        x, y, scanner, currentMatch.getCurrentMatchMap(), this);
        coreNavigationController.run();
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

}
