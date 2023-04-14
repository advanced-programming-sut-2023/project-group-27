package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuRegexes {
    ;

    private final Pattern pattern;
    LoginMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}
