package view;

import controller.view_controllers.LoginMenuController;
import view.enums.LoginMenuRegexes;
import view.enums.RegisterMenuRegexes;

import java.util.Scanner;

public class LoginMenu {
    private final LoginMenuController controller;
    private final Scanner scanner;

    public LoginMenu(LoginMenuController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }

    public String run() {
        String command , output;
        while (true){
            command = scanner.nextLine();
            if (LoginMenuRegexes.EXIT.getMatcher(command).matches()) return "Exit";
            else if (LoginMenuRegexes.LOGIN.getMatcher(command).matches())
            {
                output = controller.login(LoginMenuRegexes.LOGIN.getMatcher(command));
                System.out.println(output);
                if (output.equals("User logged in successfully!")) return "Enter main menu";
            }
            else if (LoginMenuRegexes.FORGET_PASSWORD.getMatcher(command).matches()) {
                output = controller.forgetPassword();
                System.out.println(output);
            }
            else System.out.print("Invalid command\n");
        }
    }

    public void showInformation(String info) {
        System.out.println(info);
    }

    public boolean confirm(String message, String expectedValue) {
        System.out.println(message);
        return this.scanner.nextLine().equals(expectedValue);
    }

    public String fetchAnswer() {
        return this.scanner.nextLine();
    }
}
