package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuRegexes {
    LOGOUT("\\s*LOGOUT\\s*"),
    ENTER_GAME_MENU("\\s*Enter\\s+Game Menu\\s*"),
    ENTER_MAP_EDIT_MENU("\\s*Enter\\s+Map Edit Menu\\s*"),
    ENTER_PROFILE_MENU("\\s*Enter\\s+Profile Menu");
    private final Pattern pattern;

    MainMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}
