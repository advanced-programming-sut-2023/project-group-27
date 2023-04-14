package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapEditMenuRegexes {
    ;

    private final Pattern pattern;
    MapEditMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}
