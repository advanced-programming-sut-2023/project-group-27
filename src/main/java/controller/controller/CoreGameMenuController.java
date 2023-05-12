package controller.controller;

import controller.view_controllers.GameMenuController;
import controller.view_controllers.MapEditMenuController;
import controller.view_controllers.Utilities;
import model.*;
import model.building.Building;
import model.man.Man;
import model.man.SoldierType;
import org.apache.commons.lang3.math.NumberUtils;
import view.GameMenu;
import view.MapEditMenu;

import java.util.ArrayList;
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
    private CoreShopMenuController coreShopController;
    private GameMenuController gameController;

    public CoreGameMenuController(Match currentMatch, Scanner scanner) {
        this.scanner = scanner;
        this.currentMatch = currentMatch;
        this.currentUser = currentMatch.getCurrentUser();
        this.currentMonarchy = currentUser.getMonarchy();
        this.map = currentMatch.getCurrentMatchMap();
        this.gameController = new GameMenuController(this, currentMatch);
        this.gameMenu = new GameMenu(this.gameController);
        coreTradeMenuController = new CoreTradeMenuController(currentMatch, currentMatch.getCurrentUser() , scanner);
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
        StringBuilder builder = new StringBuilder();
        builder.append("Food: ").append(currentMonarchy.calcPopularityFood()).append("\n");
        builder.append("Tax: ").append(currentMonarchy.calcPopularityTax()).append("\n");
        builder.append("Religion: ").append(currentMonarchy.calcPopularityReligion()).append("\n");
        builder.append("Fear: ").append(currentMonarchy.calcPopularityFear()).append("\n");
        return builder.toString();
    }

    public String showPopularity(){
        return String.valueOf(currentUser.getMonarchy().getPopularity());
    }

    public String showFoodList(){
        StringBuilder result = new StringBuilder();
        for (GoodsType food : GoodsType.getGranaryGoods()) {
            int num = currentUser.getMonarchy().getGranary().getGoodsCount(food);
            result.append(food.getName()).append(": ").append(num).append("\n");
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
        if (Utilities.XYCheck(x, y , map) != null) return Utilities.XYCheck(x, y , map);
        Building selectedBuilding = map.getCell(x, y).getBuilding();
        if (selectedBuilding == null)
            return "no building here\n";
        if (!selectedBuilding.getOwner().equals(currentUser))
            return "This building is not yours.";
        coreBuildingController =
                new CoreSelectBuildingMenuController(selectedBuilding , scanner, currentMonarchy);
        coreBuildingController.run();
        return null;
    }

    public String selectUnit(String xStr , String yStr , String unitType) {
        if (!NumberUtils.isNumber(xStr) || ! NumberUtils.isNumber(yStr)) {
            return "x and y should be integers";
        }
        int x = Integer.parseInt(xStr), y = Integer.parseInt(yStr);
        if (Utilities.XYCheck(x, y , map) != null) return Utilities.XYCheck(x, y , map);
        ArrayList<Selectable> theSelected = map.getCell(x , y).getSelectables();
        for (Selectable selectable : theSelected) {
            if (selectable instanceof Man) {
                if (!((Man) selectable).getOwner().equals(currentUser))
                    theSelected.remove(selectable);
            }
            if (selectable instanceof Building) {
                if (!((Building) selectable).getOwner().equals(currentUser))
                    theSelected.remove(selectable);
            }
        }
        theSelected.removeIf(selectable -> !selectable.getName().equals(unitType));
        theSelected.removeIf(selectable -> !selectable.getOwner().equals(currentMatch.getCurrentUser()));
        SoldierType type = SoldierType.getTypeByName(unitType);
        if (type == null) return "Unit type is invalid!";
        if (theSelected.size() == 0) return "There is not any unit of this type on this cell!";
        coreUnitController = new CoreSelectUnitMenuController(theSelected, currentMatch , scanner, currentUser, map, type);
        coreUnitController.run();
        return null;
    }

    public String showMap(String xStr, String yStr) {
        if (!NumberUtils.isNumber(xStr) || ! NumberUtils.isNumber(yStr)) {
            return "x and y should be integers";
        }
        int x = Integer.parseInt(xStr), y = Integer.parseInt(yStr);
        if (Utilities.XYCheck(x, y , map) != null) return Utilities.XYCheck(x, y , map);
        CoreMapNavigationMenuController coreNavigationController =
                new CoreMapNavigationMenuController(
                        x, y, scanner, currentMatch.getCurrentMatchMap());
        coreNavigationController.run();
        return null;
    }


    public void enterMapEdit(){
        CoreMapEditMenuController coreController = new CoreMapEditMenuController(currentMatch, scanner);
        coreController.run();
    }

    public String showCurrentPlayer() {
        return currentMatch.getCurrentUser().getNickname();
    }

    public void enterShop() {
        CoreShopMenuController coreShopMenuController = new CoreShopMenuController(currentMatch, scanner);
        coreShopMenuController.run();
    }
}