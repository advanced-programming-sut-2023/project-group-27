package controller.view_controllers;

import model.Cell;
import model.GameMap;
import model.Location;
import model.NaturalEntityType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
    public static Map<String, String> extractOptionsFromString(String input) {
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
            if (!result.containsKey(key)) {
                result.put(key, value);
            } else {
                return null;
            }
        }
        return result;
    }

    public static String XYCheck(int x, int y , GameMap map) {
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