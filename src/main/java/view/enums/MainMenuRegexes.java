package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuRegexes {
    LOGOUT("$\\s*LOGOUT\\s*^"),
    ENTERGAMEMENU("$\\s*Enter\\s+Game Menu\\s*^"),
    ENTERMAPEDITMENU("$\\s*Enter\\s+Map Edit Menu\\s*^"),
    ENTERPROFILEMENU("$\\s*Enter\\s+Profile Menu\\s*^");
    private final Pattern pattern;

    MainMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}
