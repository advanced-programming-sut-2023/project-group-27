package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SelectBuildingRegexes {
    ;
    private final Pattern pattern;
    SelectBuildingRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}
