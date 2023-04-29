package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuRegexes {
    LOGIN("^\\s*user\\s+login\\s*(?<options>((\\s+((-[a-zA-Z]\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))$"),
    EXIT("^\\s*exit\\s*$"),
    FORGET_PASSWORD("^\\s*forgot\\s+my\\s+password\\s*$")
    ;

    private final Pattern pattern;
    LoginMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}
