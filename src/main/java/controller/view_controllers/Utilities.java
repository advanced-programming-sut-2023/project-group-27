package controller.view_controllers;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
    static private List<String> sampleSlogans = new ArrayList<>();
    static {
        sampleSlogans = Arrays.asList(
                "I shall have my revenge, in this life or the next.",
                "Whatever It takes.",
                "Peace through Strength",
                "We quell the storm, and ride the thunder",
                "Better to die than to be a coward",
                "War wins land, Peace wins People"
        );
    }

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

    public static String randomSlogan() {
        Random random = new Random();
        return sampleSlogans.get(random.nextInt(sampleSlogans.size()));
    }

    public static String randomPassword() {
        List<Character> validLowerCase = new ArrayList<>();
        List<Character> validUpperCase = new ArrayList<>();
        List<Character> validDigits = new ArrayList<>();
        List<Character> validSpecial = new ArrayList<>();
        for (char c : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
            validLowerCase.add(c);
        }
        for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            validUpperCase.add(c);
        }
        for (char c : "0123456789".toCharArray()) {
            validDigits.add(c);
        }
        for (char c : "!@#$%^&*".toCharArray()) {
            validSpecial.add(c);
        }
        Random random = new Random();
        List<Character> characters = new ArrayList<>();
        random.ints(4).forEach(x -> characters.add(validLowerCase.get(x % 26)));
        random.ints(4).forEach(x -> characters.add(validUpperCase.get(x % 26)));
        random.ints(4).forEach(x -> characters.add(validDigits.get(x % 10)));
        random.ints(4).forEach(x -> characters.add(validSpecial.get(x % 8)));
        Collections.shuffle(characters);
        StringBuilder builder = new StringBuilder();
        return Arrays.toString(characters.toArray());
    }
}
