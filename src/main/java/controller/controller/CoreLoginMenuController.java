package controller.controller;

import controller.view_controllers.LoginMenuController;
import view.LoginMenu;

import java.util.Scanner;

public class CoreLoginMenuController {
    private final LoginMenu loginMenu;
    private final LoginMenuController loginMenuController;
    private final CoreMainMenuController coreMainMenuController;
    public CoreLoginMenuController() {
        loginMenuController = new LoginMenuController();
        loginMenu = new LoginMenu(loginMenuController);
        coreMainMenuController = new CoreMainMenuController();
    }

    public String run (Scanner scanner){
        String loginMenuResult = loginMenu.run(scanner);
        switch (loginMenuResult){
            case "Exit":
                return "Exit";
            case "Enter main menu":
                coreMainMenuController.run(scanner);
                break;
        }
        return null;
    }

    public String login(String username, String password) {
        return null;
    }

    public boolean isVerifiedPassword(String username, String securityA) {
        return true;
    }

    public String changePassword(String username, String newPassword) {
        return "New password is set successfully";
    }
}
