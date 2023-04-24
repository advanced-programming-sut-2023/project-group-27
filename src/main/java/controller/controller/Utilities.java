package controller.controller;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.*;

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
    public static String validatePassword(String newPassword) {
        if (newPassword.length() < 6) {
            return "Password is too short\n";
        }
        if (!newPassword.matches(".*[0-9].*") || !newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[a-z].*")) {
            return "Password must contain at least one uppercase letter, one lowercase letter and one digit\n";
        }
        if (newPassword.matches("[0-9a-zA-Z_]+")) {
            return "Password must contain at least one special character\n";
        }
        return null;
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

    public static String randomSlogan() {
        Random random = new Random();
        return sampleSlogans.get(random.nextInt(sampleSlogans.size()));
    }

    public static String encryptString(String originalContent) {
        return Hashing.sha256()
                .hashString(originalContent, StandardCharsets.UTF_8)
                .toString();
    }
}
