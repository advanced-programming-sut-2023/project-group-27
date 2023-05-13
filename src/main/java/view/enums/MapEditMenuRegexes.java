package view.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapEditMenuRegexes {
    SET_TEXTURE("\\s*settexture(?<data>(\\s+-[xyt]\\s+\\S+){3})"),
    SET_TEXTURE2("\\s*settexture(?<data>(\\s+-((x1)|(x2)|(y1)|(y2)|t)\\s+\\S+){5})"),
    CLEAR("\\s*clear(?<data>(\\s+-[xy]\\s+\\S+){2})"),
    DROP_ROCK("\\s*droprock(?<data>(\\s+-[xyd]\\s+\\S+){3})"),
    DROP_TREE("\\s*droptree(?<data>(\\s+-[xyt]\\s+\\S+){3})"),
    DROP_BUILDING("\\s*dropbuilding(?<data>(\\s+-[xyt]\\s+\\S+){3})"),
    DROP_UNIT("\\s*dropunit(?<data>(\\s+-[xytc]\\s+\\S+){4})"),
    EXIT("\\s*Exit\\s*");

    private final Pattern pattern;
    MapEditMenuRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return this.pattern.matcher(input);
    }
}
