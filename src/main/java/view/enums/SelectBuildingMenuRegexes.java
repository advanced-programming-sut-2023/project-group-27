package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SelectBuildingMenuRegexes {
    CREATEUNIT("\\s*create\\s+unit\\s+(?<fields>(-t\\s+\\S+\\s+-c\\s+\\d+)|(-c\\s+\\d+\\s+-t\\s+\\S+))\\s*"),
    REPAIR("\\s*repair\\s*"),
    EXIT("\\s*Exit\\s*");
    private final Pattern pattern;
    SelectBuildingMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}