package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapNavigationMenuRegexes {
    MOVEMAP("^\\s*map\\s*(?<directionAndValue>\\s+((up)|(down)|(left)|(right))(\\s+-?\\d+)?)+\\s*$"),
    SHOWDETAILS("^\\s*show\\s+details\\s*(?<coordinate>\\s+(((-x)|(-y))\\s+\\d+)){2}}\\s*$"),
    EXIT("^\\s*Exit\\s*&");
    private final Pattern pattern;
    MapNavigationMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}