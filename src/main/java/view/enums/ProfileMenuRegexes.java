package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuRegexes {
    CHANGE_USERNAME("profile change -u (?<username>\\S+)"),
    CHANGE_NICKNAME("profile change -n (?<nickname>\\S+)"),
    CHANGE_PASSWORD("profile change -o (?<oldPassword>\\S+) -n (?<newPassword>\\S+)"),
    CHANGE_EMAIL("profile change -e (?<email>\\S+)"),
    CHANGE_SLOGAN("profile change slogan -s (?<slogan>\\S+)"),
    REMOVE_SLOGAN("profile remove slogan"),
    DISPLAY_HIGH_SCORE("profile display highscore"),
    DISPLAY_RANK("profile display rank"),
    DISPLAY_SLOGAN("profile display slogan"),
    DISPLAY_PROFILE("profile display");

    private final Pattern pattern;
    ProfileMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}
