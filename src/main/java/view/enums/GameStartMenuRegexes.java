package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameStartMenuRegexes {
    SHOW_ALL_PLAYERS(""),
    SHOW_ALL_MAPS(""),
    SHOW_GAME_PLAYERS(""),
    SHOW_GAME_MAP_NAME(""),
    NAVIGATE_SELECTED_MAP(""),
    ADD_PLAYER(""),
    REMOVE_PLAYER(""),
    SELECT_MAP(""),
    SHOW_COLORS(""),
    SET_COLOR(""),
    REMOVE_COLOR(""),
    START_GAME("");
    private final Pattern pattern;

    GameStartMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}