package controller.view_controllers;

import controller.controller.CoreGameStartMenuController;
import model.MonarchyColorType;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameStartMenuController {
    private final CoreGameStartMenuController coreController;

    public GameStartMenuController(CoreGameStartMenuController coreController) {
        this.coreController = coreController;
    }

    public String showAllUsers() {
        return coreController.showAllUsers();
    }

    public String showAllMaps() {
        return coreController.showAllMaps();
    }

    public String showAllColors() {
        return coreController.showAllColors();
    }

    public String showGamePlayers() {
        return coreController.showGamePlayers();
    }

    public String showSelectedMapInfo() {
        return coreController.showSelectedMapInfo();
    }

    public String navigateSelectedMap() {
        return coreController.navigateSelectedMap();
    }

    public String addPlayer(String input) {
        return coreController.addPlayer(Integer.parseInt(input));
    }

    public String removePlayer(String input) {
        return coreController.removePlayer(Integer.parseInt(input));
    }

    public String selectMap(String input) {
        return coreController.selectMap(Integer.parseInt(input));
    }

    public String resetColors() {
        coreController.resetColors();
        return "all assigned colors are removed.";
    }

    public String setColor(String input) {
        Pattern patternInteger = Pattern.compile("\\d+");
        Pattern patternType = Pattern.compile("[^0-9\\s]+");
        Matcher matcherInteger = patternInteger.matcher(input);
        Matcher matcherType = patternType.matcher(input);

        if (!matcherInteger.find() || !matcherType.find())
            return "invalid inputs for colorType and player number.";

        MonarchyColorType type;
        if ((type = MonarchyColorType.getTypeByName(matcherType.group())) == null)
            return "invalid color!";

        return coreController.setColor(Integer.parseInt(matcherInteger.group()), type);
    }

    public String assignKeepsAndStart(String input) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Pattern numberPattern = Pattern.compile("\\d+");
        Matcher matcher = numberPattern.matcher(input);
        while (matcher.find())
            numbers.add(Integer.parseInt(matcher.group()));

        return coreController.assignKeepsAndStart(numbers);
    }
}