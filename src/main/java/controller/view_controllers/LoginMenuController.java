package controller.view_controllers;

import controller.controller.CoreLoginMenuController;
import model.StrongholdCrusader;
import model.User;
import view.LoginMenu;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenuController {
    private final LoginMenu menu;
    private final CoreLoginMenuController coreController;

    public LoginMenuController(CoreLoginMenuController coreController, Scanner scanner) {
        this.menu = new LoginMenu(this, scanner);
        this.coreController = coreController;
    }

    public LoginMenu getMenu() {
        return menu;
    }

    public String login(Matcher matcher) {
        String argsString = matcher.group("options");
        Map<String , String> args = Utilities.extractOptionsFromString(argsString);
        if (args == null)
            return "Do not determine same field more than once!";
        if (!args.containsKey("u")) return "Please enter your username";
        if (!args.containsKey("p")) return "Please enter your password";
        String username = args.get("u");
        String password = args.get("p");
        boolean stayLoggedIn = args.containsKey("stay-logged-in");
        return coreController.login(username , password , stayLoggedIn);
    }

    public String forgetPassword() {
        menu.showInformation("Please enter your username");
        String username = menu.fetchAnswer();
        if (!StrongholdCrusader.getAllUsers().containsKey(username))
            return "This username doesn't exist!";
        User user = StrongholdCrusader.getAllUsers().get(username);
        menu.showInformation("Please answer the security question");
        if (!menu.confirm(user.getSecurityQ() , user.getSecurityA()))
            return "Wrong answer!";
        menu.showInformation("Enter a new password");
        String newPassword = menu.fetchAnswer();
        while (!menu.confirm("Please confirm your new password" , newPassword)) {
            menu.showInformation("The confirmation doesn't match the password");
        }
        String result = coreController.forgetPassword(user , newPassword);
        while (!result.equals("Password changed successfully!")) {
            menu.showInformation(result);
            newPassword = menu.fetchAnswer();
            result = coreController.forgetPassword(user , newPassword);
        }
        return "Password changed successfully!";
    }

}
