package controller.view_controllers;

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
}
