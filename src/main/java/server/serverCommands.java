package server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum serverCommands {
    GET_GAMES("get games"),
    CREATE_GAME("create game -m (?<mapIndex>\\d+) -c (?<capacity>\\d+) -o (?<owner>\\S+) -v (?<visibility>\\S+)"),
    JOIN_GAME("join game"),
    START_GAME("start game");

    private Pattern pattern;
    serverCommands(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String command) {
        return pattern.matcher(command);
    }
}
