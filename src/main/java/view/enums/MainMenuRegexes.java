package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuRegexes {
    LOGOUT("\\s*Logout\\s*"),
    ENTER_GAME_START_MENU("\\s*Enter\\s+game start menu\\s*"),
    ENTER_MAP_EDIT_MENU("\\s*Enter\\s+map edit menu\\s*"),
    ENTER_PROFILE_MENU("\\s*Enter\\s+profile menu");
    private final Pattern pattern;

    MainMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}
