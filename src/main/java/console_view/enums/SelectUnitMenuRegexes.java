package console_view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SelectUnitMenuRegexes {
    EXIT("\\s*Exit\\s*"),
    MOVETO("\\s*move\\s+unit\\s+to\\s*(?<options>((\\s+((-[a-zA-Z0-9]+\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z0-9]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    PATROL_UNIT("\\s*patrol\\s+unit\\s*(?<options>((\\s+((-[a-zA-Z0-9]+\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z0-9]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    SET_STATUS("\\s*set\\s*(?<options>((\\s+((-[a-zA-Z0-9]+\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z0-9]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    ATTACK("\\s*attack\\s*(?<options>((\\s+((-[a-zA-Z0-9]+\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    POUR_OIL("\\s*pour\\s+oil\\s*(?<options>((\\s+((-[a-zA-Z0-9]+\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    DIG_TUNNEL("\\s*dig\\s+tunnel\\s*(?<options>((\\s+((-[a-zA-Z0-9]+\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    BUILD("\\s*build\\s*(?<options>((\\s+((-[a-zA-Z0-9]+\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    DISBAND("\\s*disband\\s*(?<options>((\\s+((-[a-zA-Z0-9]+\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    ;

    private final Pattern pattern;

    SelectUnitMenuRegexes(String regex) {
        pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return pattern.matcher(input);
    }
}
