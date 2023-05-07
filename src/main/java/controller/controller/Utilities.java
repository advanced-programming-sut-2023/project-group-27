package controller.controller;

import com.google.common.hash.Hashing;
import model.*;
import model.man.Engineer;
import model.man.Man;
import model.man.Soldier;
import model.man.SoldierType;

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

    public static String validateEmail(String email) {
        if (!email.matches("[0-9a-zA-Z_]+@[0-9a-zA-Z_]+\\.[0-9a-zA-Z_]+")) {
            return "Invalid email format\n";
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

    public static Man getNewMan(SoldierType type, User owner) {
        if (type == SoldierType.ENGINEER)
            return new Engineer(SoldierType.ENGINEER.getHitpoint(), owner);
        return new Soldier(type, owner);
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