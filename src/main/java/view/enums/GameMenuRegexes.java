package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuRegexes {
    SHOW_MAP("\\s*show\\s+map\\s*(?<options>((\\s+((-[a-zA-Z]\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    SHOW_POPULARITY_FACTORS("\\s*show\\s+popularity\\s+factors\\s*"),
    SHOW_POPULARITY("\\s*show\\s+popularity\\s*"),
    SHOW_FOOD_LIST("\\s*show\\s+food\\s+list\\s*"),
    SET_FOOD_RATE("\\s*food\\s+rate\\s*(?<options>((\\s+((-[a-zA-Z]\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    SHOW_FOOD_RATE("\\s*food\\s+rate\\s+show\\s*"),
    SET_TAX_RATE("\\s*tax\\s+rate\\s*(?<options>((\\s+((-[a-zA-Z]\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    SHOW_TAX_RATE("\\s*tax\\s+rate\\s+show\\s*"),
    SET_FEAR_RATE("\\s*fear\\s+rate\\s*(?<options>((\\s+((-[a-zA-Z]\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    SHOW_FEAR_RATE("\\s*fear\\s+rate\\s+show\\s*"),
    DROP_BUILDING("\\s*dropbuilding\\s*(?<options>((\\s+((-[a-zA-Z]\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    SELECT_BUILDING("\\s*select\\s+building\\s*(?<options>((\\s+((-[a-zA-Z]\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))"),
    SELECT_UNIT("\\s*select\\s+unit\\s*(?<options>((\\s+((-[a-zA-Z]\\s+((\"[^\"]+\")|(\\S+)))|(--[a-zA-Z]+\\s+((\"[^\"]+\")|(\\S+)))))+))");

    private final Pattern pattern;
    GameMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}
