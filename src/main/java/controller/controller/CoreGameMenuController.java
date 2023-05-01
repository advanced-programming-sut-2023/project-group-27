package controller.controller;

import controller.view_controllers.GameMenuController;
import model.Monarchy;
import model.GameMap;
import model.GoodsType;
import model.StrongholdCrusader;
import model.building.Building;
import model.man.Man;
import org.apache.commons.lang3.math.NumberUtils;
import view.GameMenu;

import java.util.Scanner;

public class CoreGameMenuController {
    private final Scanner scanner;
    private Monarchy currentMonarchy;
    private User loggedInUser;
    private final GameMap map;
    private final GameMenu gameMenu;
    private final CoreTradeMenuController coreTradeMenuController;
    private CoreSelectUnitMenuController coreUnitController;
    private CoreSelectBuildingMenuController coreBuildingController;

    public CoreGameMenuController(Monarchy currentMonarchy, Scanner scanner) {
        this.scanner = scanner;
        this.currentMonarchy = currentMonarchy;
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
        // TODO implement here
        return null;
    }

    public String showPopularity(){
        return String.valueOf(loggedInUser.getMonarchy().calcPopularity());
    }

    public String showFoodList(){
        StringBuilder result = new StringBuilder();
        for (GoodsType food : GoodsType.getGranaryGoods()) {
            int num = loggedInUser.getMonarchy().getStorage().getOrDefault(food, 0);
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
        loggedInUser.getMonarchy().setFoodRate(rate);
        return "success\n";
    }

    public String showFoodRate(){
        return String.valueOf(loggedInUser.getMonarchy().getFoodRate());
    }

    public String setTaxRate(String rateStr){
        if (!NumberUtils.isNumber(rateStr)) return "rate should be an integer\n";
        int rate = Integer.parseInt(rateStr);
        if (rate < -3 || rate > 8) return "rate should be between -3 and 8\n";
        loggedInUser.getMonarchy().setTaxRate(rate);
        return "success\n";
    }

    public String showTaxRate(){
        return String.valueOf(loggedInUser.getMonarchy().getTaxRate());
    }

    public String setFearRate(String rateStr){
        if (!NumberUtils.isNumber(rateStr)) return "rate should be an integer\n";
        int rate = Integer.parseInt(rateStr);
        if (rate < -5 || rate > 5) return "rate should be between -5 and 5\n";
        loggedInUser.getMonarchy().setFearRate(rate);
        return "success\n";
    }

    public String showFearRate(){
        return String.valueOf(loggedInUser.getMonarchy().getFearRate());
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
                new CoreSelectBuildingMenuController(selectedBuilding , scanner);
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
                        x, y, scanner, StrongholdCrusader.getCurrentMap(), this);
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
