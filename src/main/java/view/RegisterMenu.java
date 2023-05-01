package view;

import controller.view_controllers.RegisterMenuController;
import controller.view_controllers.Utilities;
import view.enums.RegisterMenuRegexes;

import java.util.Map;
import java.util.Scanner;

public class RegisterMenu extends MenuBase{
    private final RegisterMenuController controller;

    public RegisterMenu(RegisterMenuController controller, Scanner scanner) {
        super(scanner);
        this.controller = controller;
    }

    public String run() {
        System.out.println("Entered Register Menu");
        String command = this.scanner.nextLine();
        while (!RegisterMenuRegexes.EXIT.getMatcher(command).matches()) {
            if (RegisterMenuRegexes.RAW_REGISTER.getMatcher(command).matches()) {
                String output;
                output = controller.createUser(
                        RegisterMenuRegexes.RAW_REGISTER.getMatcher(command));
                System.out.print(output);
                command = scanner.nextLine();
                continue;
            }
            if (command.equals("login")) {
                return "Enter login menu";
            }
            System.out.print("Invalid command\n");
            command = scanner.nextLine();
        }
        return "Exit";
    }

    @Override
    public String fetchAnswer() {
        String answer = this.scanner.nextLine();
        if (!RegisterMenuRegexes.SECURITY_A.getMatcher(answer).matches()) {
            return null;
        }
        return answer;
    }
}
