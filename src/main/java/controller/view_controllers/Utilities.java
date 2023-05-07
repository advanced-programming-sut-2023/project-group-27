package controller.view_controllers;

import model.building.CivilBuildingType;
import model.building.ProductionBuildingType;
import model.castle_components.CastleComponentType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
    protected static Map<String, String> extractOptionsFromString(String input) {
        Map<String, String> result = new HashMap<>();
        String args = "((-(?<option>[a-zA-Z])\\s+(?<optionValue>(\"[^\"]+\")|(\\S+)))|(--(?<flag>[a-zA-Z]+)\\s+(?<flagValue>(\"[^\"]+\")|(\\S+))))";
        Pattern pattern = Pattern.compile(args);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String key;
            String value;

            if (matcher.group("option") != null) {
                key = matcher.group("option");
                value = matcher.group("optionValue");
            } else {
                key = matcher.group("flag");
                value = matcher.group("flagValue");
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

        while (matcher.matches()) {
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
}