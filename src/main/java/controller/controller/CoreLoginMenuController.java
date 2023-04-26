package controller.controller;

import controller.view_controllers.LoginMenuController;
import model.StrongholdCrusader;
import model.User;
import view.LoginMenu;

import java.util.Scanner;

public class CoreLoginMenuController {
    private final Scanner scanner;
    private final LoginMenu loginMenu;
    private final LoginMenuController loginController;
    private final CoreMainMenuController coreMainMenuController;
    public CoreLoginMenuController(Scanner scanner) {
        this.scanner = scanner;
        this.loginController = new LoginMenuController(this, scanner);
        loginMenu = loginController.getMenu();
        coreMainMenuController = new CoreMainMenuController(scanner);
    }

    public String run (){
        String loginMenuResult;
        while (true) {
            loginMenuResult = loginMenu.run();
            switch (loginMenuResult) {
                case "Exit":
                    return "Exit";
                case "Enter main menu":
                    coreMainMenuController.run();
                    break;
            }
        }
    }
    static int delay = 0;
    static long delayStart , currentTime;
    public String login(String username, String password , boolean stayLoggedIn) {
        currentTime = System.currentTimeMillis();
        if ((currentTime - delayStart) / 1000 < delay)
            return "You need to wait another " + (delay - (currentTime - delayStart) / 1000) + "seconds!";
        if (!StrongholdCrusader.getAllUsers().containsKey(username))
            return "This username doesn't exist!";
        User user = StrongholdCrusader.getAllUsers().get(username);
        if (!user.isPasswordCorrect(password)) {
            if (delay == 0) delayStart = System.currentTimeMillis();
            delay += 5;
            return "Username and password didn't match!";
        }
        if (stayLoggedIn) user.setStayLoggedIn(true);
        delay = 0;
        return "User logged in successfully!";
    }

    public String forgetPassword() {
        return null;
    }

}
