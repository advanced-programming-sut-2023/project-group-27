package view;

import controller.view_controllers.LoginMenuController;
import view.enums.LoginMenuRegexes;

import java.util.Scanner;

public class LoginMenu extends MenuBase{
    private final LoginMenuController controller;

    public LoginMenu(LoginMenuController controller, Scanner scanner) {
        super(scanner);
        this.controller = controller;
    }

    public String run() {
        System.out.println("Entered Login Menu");
        System.out.print("your options are:\n" +
                "1. user login -u <username> -p <password>\n" +
                "2. forgot my password -u <username>\n" +
                "3. Exit\n");
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
            else System.out.println("Invalid command!");
        }
    }

}
