package controller.controller;

import controller.view_controllers.LoginMenuController;
import model.StrongholdCrusader;
import model.User;
import view.LoginMenu;

import java.io.IOException;
import java.util.Scanner;

public class CoreLoginMenuController {
    private final Scanner scanner;
    private final LoginMenu loginMenu;
    private final LoginMenuController loginController;

    public CoreLoginMenuController(Scanner scanner) {
        this.scanner = scanner;
        this.loginController = new LoginMenuController(this, scanner);
        loginMenu = loginController.getMenu();
    }

    public LoginMenuController getLoginController() {
        return loginController;
    }

    public String run () throws IOException {
        String loginMenuResult;
        while (true) {
            loginMenuResult = loginMenu.run();
            switch (loginMenuResult) {
                case "Exit":
                    return "Exit";
                case "Enter main menu":
                    CoreMainMenuController coreMainMenuController =
                            new CoreMainMenuController(scanner);
                    coreMainMenuController.run();
                    break;
            }
        }
    }
    private static int delay = 0;
    private static long delayStart;

    public String login(String username, String password , boolean stayLoggedIn) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - delayStart) / 1000 < delay)
            return "You need to wait another " + (delay - (currentTime - delayStart) / 1000) + " seconds to login!";
        if (!StrongholdCrusader.getAllUsers().containsKey(username))
            return "This username does not exist!";
        User user = StrongholdCrusader.getAllUsers().get(username);
        if (!user.isPasswordCorrect(password)) {
            delayStart = System.currentTimeMillis();
            delay += 5;
            return "Username and password did not match!";
        }
        if (stayLoggedIn) user.setStayLoggedIn(true);
        delay = 0;
        StrongholdCrusader.setLoggedInUser(user);
        return "User logged in successfully!";
    }

    public String forgetPassword(User user , String newPassword) {
        String validateResult = Utilities.validatePassword(newPassword);
        if (validateResult != null) return validateResult;
        user.setPassword(newPassword);
        return "Password changed successfully!";
    }

    public static void resetDelay() {
        delay = 0;
        delayStart = 0;
    }

}
