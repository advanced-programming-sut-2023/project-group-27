package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameStartMenuRegexes {
    SHOW_ALL_USERS("\\s*show\\s+all\\s+users\\s*"),
    SHOW_ALL_MAPS("\\s*show\\s+all\\s+maps\\s*"),
    SHOW_GAME_PLAYERS("\\s*show\\s+selected\\s+players\\s*"),
    SHOW_SELECTED_MAP_INFO("\\s*show\\s+selected\\s+map\\s+info\\s*"),
    NAVIGATE_SELECTED_MAP("\\s*navigate\\s+selected\\s+map\\s*"),
    ADD_PLAYER("\\s*add\\s+player\\s+(?<data>\\d+)\\s*"),
    REMOVE_PLAYER("\\s*remove\\s+player\\s+(?<data>\\d+)\\s*"),
    SELECT_MAP("\\s*select\\s+map\\s+(?<data>\\d+)\\s*"),
    SHOW_COLORS("\\s*show\\s+all\\s+colors\\s*"),
    SET_COLOR("\\s*set\\s+color\\s*(?<data>(\\s+\\S+){2})"),
    RESET_COLORS("\\s*reset\\s+colors\\s*"),
    ASSIGN_KEEPS_AND_START("\\s*assign\\s+keeps\\s+and\\s+start(?<data>(\\s+\\d+)+)"),
    ADD_MAP("add map"),
    EXIT("\\s*Exit\\s*");
    private final Pattern pattern;

    GameStartMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}