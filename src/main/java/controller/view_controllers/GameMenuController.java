package controller.view_controllers;

import controller.controller.CoreGameMenuController;
import model.GameMap;
import model.Match;
import model.StrongholdCrusader;
import model.User;
import model.man.SoldierType;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;
import java.util.regex.Matcher;

public class GameMenuController {
    private final CoreGameMenuController coreController;
    private final GameMap map;
    private final Match currentMatch;

    public GameMenuController(CoreGameMenuController coreController, Match match) {
        this.coreController = coreController;
        this.currentMatch = match;
        this.map = match.getCurrentMatchMap();
    }

    public String showPopularityFactors(){
        return null;
    }

    public String showPopularity(){
        return coreController.showPopularity();
    }

    public String showFoodList(){
        return coreController.showFoodList();
    }

    public String setFoodRate(Matcher matcher){
        Map<String, String> args = getArgs(matcher);
        String x = getError(!args.containsKey("r"), "provide rate\n", args, 1);
        if (x != null) return x;
        String rateStr = args.get("r");
        return coreController.setFoodRate(rateStr);
    }

    public String showFoodRate(){
        return coreController.showFoodRate();
    }

    public String setTaxRate(Matcher matcher){
        Map<String, String> args = getArgs(matcher);
        String x = getError(!args.containsKey("r"), "provide rate\n", args, 1);
        if (x != null) return x;
        String rateStr = args.get("r");
        return coreController.setTaxRate(rateStr);
    }

    public String showTaxRate(){
        return coreController.showTaxRate();
    }

    public String setFearRate(Matcher matcher){
        Map<String, String> args = getArgs(matcher);
        String x = getError(!args.containsKey("r"), "provide rate\n", args, 1);
        if (x != null) return x;
        String rateStr = args.get("r");
        return coreController.setFearRate(rateStr);
    }

    public String showFearRate(){
        return coreController.showFearRate();
    }

    public String dropBuilding(Matcher matcher){
        return null;
    }

    public String selectBuilding(Matcher matcher){
        Map<String, String> args = getArgs(matcher);
        String x = getError(!args.containsKey("x") || !args.containsKey("y"),
                "provide x and y\n", args, 2);
        if (x != null) return x;
        String xStr = args.get("x");
        String yStr = args.get("y");
        return coreController.selectBuilding(xStr, yStr);
    }

    public String selectUnit(Matcher matcher){
        Map<String, String> args = getArgs(matcher);
        String x = getError(!args.containsKey("x") || !args.containsKey("y") || !args.containsKey("t"),
                "provide x and y and type\n", args, 3);
        if (x != null) return x;
        String xStr = args.get("x");
        String yStr = args.get("y");
        String unitType = args.get("t");
        if (SoldierType.getTypeByName(unitType) == null) return "Invalid unit type!";
        return coreController.selectUnit(xStr, yStr , unitType);
    }

    public String showMap(Matcher matcher) {
        Map<String, String> args = getArgs(matcher);
        String x = getError(!args.containsKey("x") || !args.containsKey("y"),
                "provide x and y\n", args, 2);
        if (x != null) return x;
        String xStr = args.get("x");
        String yStr = args.get("y");
        return coreController.showMap(xStr, yStr);
    }

    public void enterShop() {
        coreController.enterShop();
    }

    private static String getError(boolean checkRequiredArgs, String error, Map<String, String> args, int expectedSize) {
        if (checkRequiredArgs) {
            return error;
        }
        if (args.size() > expectedSize) {
            return "additional arguments found!\n";
        }
        return null;
    }

    private static Map<String, String> getArgs(Matcher matcher) {
        matcher.matches();
        String options = matcher.group("options");
        Map<String, String> args = Utilities.extractOptionsFromString(options);
        return args;
    }

    public void enterMapEdit() {
        coreController.enterMapEdit();
    }

    public String showCurrentPlayer() {
        return coreController.showCurrentPlayer();
    }
}