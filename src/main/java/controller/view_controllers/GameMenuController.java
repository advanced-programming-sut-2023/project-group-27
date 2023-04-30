package controller.view_controllers;

import controller.controller.CoreGameMenuController;
import model.GameMap;
import model.StrongholdCrusader;
import model.User;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;
import java.util.regex.Matcher;

public class GameMenuController {
    private final CoreGameMenuController coreController;
    private final GameMap map;
    private final User loggedInUser;

    public GameMenuController(CoreGameMenuController coreController, User loggedInUser) {
        this.coreController = coreController;
        this.loggedInUser = loggedInUser;
        this.map = StrongholdCrusader.getCurrentMap();
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
        matcher.matches();
        String options = matcher.group("options");
        Map<String, String> args = Utilities.extractOptionsFromString(options);
        if (!args.containsKey("x") || !args.containsKey("y")) {
            return "privide x and y\n";
        }
        if (args.size() > 2) {
            return "aditional arguments found!\n";
        }
        String xStr = args.get("x");
        String yStr = args.get("y");
        return coreController.selectBuilding(xStr, yStr);
    }

    public String selectUnit(Matcher matcher){
        matcher.matches();
        String options = matcher.group("options");
        Map<String, String> args = Utilities.extractOptionsFromString(options);
        if (!args.containsKey("x") || !args.containsKey("y")) {
            return "privide x and y\n";
        }
        if (args.size() > 2) {
            return "aditional arguments found!\n";
        }
        String xStr = args.get("x");
        String yStr = args.get("y");
        return coreController.selectUnit(xStr, yStr);
    }
}
