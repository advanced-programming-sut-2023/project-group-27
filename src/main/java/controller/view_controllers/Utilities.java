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

    public static boolean checkBounds(Location location, GameMap gameMap) {
        return ((location.x >= 0 && location.x < gameMap.getWidth()) && (location.y >= 0 && location.y < gameMap.getHeight()));
    }

    public static boolean checkRectanglePoints(Location location1, Location location2, GameMap gameMap) {
        return location1.x <= location2.x && location1.y <= location2.y;
    }

    public static boolean checkTextureChangePermission(Cell cell) {
        return (cell.getBuilding() == null && cell.getNaturalEntityType() == null && cell.getMen().size() == 0);
    }

    public static NaturalEntityType getRandomRock() {
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction) {
            case 0:
                return NaturalEntityType.ROCKNORTH;
            case 1:
                return NaturalEntityType.ROCKEAST;
            case 2:
                return NaturalEntityType.ROCKSOUTH;
            default:
                return NaturalEntityType.ROCKWEST;
        }
    }
}