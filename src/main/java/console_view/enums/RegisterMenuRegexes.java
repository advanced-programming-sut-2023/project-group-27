package console_view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuRegexes {
    EXIT("Exit"),
    RAW_REGISTER("\\s*user\\s+create\\s*(?<options>((\\s+((-[a-zA-Z0-9]+\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    SECURITY_A("\\s*question\\s+pick\\s*(?<options>((\\s+((-[a-zA-Z0-9]+\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))");
    private final Pattern pattern;
    RegisterMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}
