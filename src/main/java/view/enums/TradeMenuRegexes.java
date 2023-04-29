package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeMenuRegexes {
    CREATE_TRADE("\\s*trade\\s*(?<options>((\\s+((-[a-zA-Z]\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    TRADE_LIST("\\s*trade\\s+list\\s+"),
    TRADE_HISTORY("\\s*trade\\s+history\\s+"),
    TRADE_ACCEPT("\\s*trade\\s+accept\\s*(?<options>((\\s+((-[a-zA-Z]\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))");

    private final Pattern pattern;
    TradeMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}
