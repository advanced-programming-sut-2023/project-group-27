package view;

import view.enums.RegisterMenuRegexes;

import java.util.Scanner;

public class MenuBase {
    protected final Scanner scanner;

    public MenuBase(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showInformation(String info) {
        System.out.println(info);
    }

    public boolean confirm(String message, String expectedValue) {
        System.out.println(message);
        return this.scanner.nextLine().equals(expectedValue);
    }

    public String fetchAnswer() {
        String answer = this.scanner.nextLine();
        return answer;
    }
}
