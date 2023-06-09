package controller.view_controllers;

import model.GameMap;
import model.building.CivilBuilding;
import model.building.CivilBuildingType;
import model.building.ProductionBuildingType;
import model.castle_components.CastleComponentType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
    protected static Map<String, String> extractOptionsFromString(String input) {
        Map<String, String> result = new HashMap<>();
        String args = "((-(?<option>[a-zA-Z0-9]+)\\s+(?<optionValue>(\"[^\"]+\")|(\\S+)))|(--(?<flag>[a-zA-Z0-9]+)\\s+(?<flagValue>(\"[^\"]+\")|(\\S+)))|(--(?<slif>stayLoggedIn)))";
        Pattern pattern = Pattern.compile(args);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String key;
            String value;

            if (matcher.group("option") != null) {
                key = matcher.group("option");
                value = matcher.group("optionValue");
            } else if (matcher.group("flag") != null){
                key = matcher.group("flag");
                value = matcher.group("flagValue");
            }else {
                key = matcher.group("slif");
                value = null;
            }
            if (!result.containsKey(key))
                result.put(key, value);
            else return null;
        }
        return result;
    }

    protected static HashMap<String, String> extractInputs(String input) {
        HashMap <String, String>  outputMap = new HashMap<>();
        Pattern pattern = Pattern.compile("-(?<key>[xydtc]\\d*)\\s+(?<value>\\S+)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            if (outputMap.containsKey(matcher.group("key")))
                return null;
            outputMap.put(matcher.group("key"), matcher.group("value"));
        }
        return outputMap;
    }

    protected static Object getBuildingTypeByName(String input) {
        ProductionBuildingType productionBuildingType = ProductionBuildingType.getTypeByName(input);
        if (productionBuildingType != null)
            return productionBuildingType;

        CivilBuildingType civilBuildingType = CivilBuildingType.getTypeByName(input);
        if (civilBuildingType != null)
            return civilBuildingType;

        return CastleComponentType.getTypeByName(input);
    }
    public static String XYCheck ( int x, int y, GameMap map){
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

    public static HashMap<Object, String> getAllBuildingNames() {
        HashMap<Object, String> output = new HashMap<>();
        for (CastleComponentType value : CastleComponentType.values())
            output.put(value, value.getName());
        for (ProductionBuildingType value : ProductionBuildingType.values())
            output.put(value, value.getName());
        for (CivilBuildingType value : CivilBuildingType.values())
            output.put(value, value.getName());
        return output;
    }
}