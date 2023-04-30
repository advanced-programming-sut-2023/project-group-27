package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopMenuRegexes {
    EXIT("^\\s*Exit\\s*$"),
    SHOWPRICELIST("^\\s*show\\s+price\\s+list\\s*$"),
    BUY("^\\s*buy\\s*(?<options>((\\s+((-[a-zA-Z]\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))$"),
    SELL("^\\s*sell\\s*(?<options>((\\s+((-[a-zA-Z]\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))$");

    private final Pattern pattern;

    ShopMenuRegexes(String regex) {
        pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return pattern.matcher(input);
    }
}
