package controller.view_controllers;

import controller.controller.CoreLoginMenuController;
import view.LoginMenu;

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
        return null;
    }

    public boolean isVerifiedPassword(Matcher matcher) {
        return true;
    }

    public String changePassword(Matcher matcher) {
        return "New password is set successfully";
    }

}
