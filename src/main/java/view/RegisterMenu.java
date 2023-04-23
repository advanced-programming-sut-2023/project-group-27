package view;

import controller.view_controllers.RegisterMenuController;
import controller.view_controllers.Utilities;
import view.enums.RegisterMenuRegexes;

import java.util.Map;
import java.util.Scanner;

public class RegisterMenu {
    private final RegisterMenuController controller;
    private final Scanner scanner;

    public RegisterMenu(RegisterMenuController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public String run() {
        String command = this.scanner.nextLine();
        while (!RegisterMenuRegexes.EXIT.getMatcher(command).matches()) {
            if (RegisterMenuRegexes.RAW_REGISTER.getMatcher(command).matches()) {
                String output;
                output = controller.createUser(
                        RegisterMenuRegexes.RAW_REGISTER.getMatcher(command));
                System.out.print(output);
                continue;
            }
            System.out.print("Invalid command\n");
        }
        // TODO return something that would handle menu navigation: MazMaz
        return null;
    }

    public void showInformation(String info) {
        System.out.println(info);
    }

    public boolean confirm(String message, String expectedValue) {
        String confirmation = "";
        System.out.println(message);
        return this.scanner.nextLine().equals(expectedValue);
    }

    public String fetchAnswer() {
        String answer = this.scanner.nextLine();
        if (!RegisterMenuRegexes.SECURITY_A.getMatcher(answer).matches()) {
            return null;
        }
        return answer;
    }
}
