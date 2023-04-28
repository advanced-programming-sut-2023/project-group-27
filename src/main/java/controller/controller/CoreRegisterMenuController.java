package controller.controller;

import controller.view_controllers.RegisterMenuController;
import model.StrongholdCrusader;
import model.User;
import view.RegisterMenu;

import java.util.Scanner;

public class CoreRegisterMenuController {

    private User rawUser;
    private final RegisterMenu registerMenu;
    private final RegisterMenuController registerController;
    private final CoreLoginMenuController coreLoginMenuController;

    public CoreRegisterMenuController(Scanner scanner){
        this.registerController = new RegisterMenuController(this, scanner);
        registerMenu = this.registerController.getMenu();
        coreLoginMenuController = new CoreLoginMenuController(scanner);
    }

    public RegisterMenuController getRegisterController() {
        return registerController;
    }

    public String run(){
        String registerMenuResult;
        while (true) {
            registerMenuResult = registerMenu.run();
            switch (registerMenuResult){
                case "Exit":
                    return "Exit";
                case "Enter login menu":
                    if (coreLoginMenuController.run().equals("Exit")) return "Exit";
                    break;
            }
        }
    }
    public String initializeUser(
            String username, String password, String email, String nickname, String slogan) {
        if (username.equals("") || password.equals("") ||
                email.equals("") || nickname.equals("")) {
            return "Please fill required fields";
        }
        if (!username.matches("[0-9a-zA-Z_]+")) {
            return "Invalid username format";
        }
        if (StrongholdCrusader.getAllUsers().containsKey(username)) {
            return "Username already exists";
        }
        String passwordError = Utilities.validatePassword(password);
        if (passwordError != null) {
            return passwordError;
        }
        if (Utilities.validateEmail(email) != null) {
            return Utilities.validateEmail(email);
        }
        if (StrongholdCrusader.getAllUsers().values().stream().anyMatch(user -> user.getEmail().equals(email))) {
            return "Email already exists";
        }

        rawUser = new User(username, password, nickname, "", email, "", "");
        return null;
    }

    public String finalizeUser(String securityQuestion, String securityAnswer) {
        if (securityQuestion.equals("") || securityAnswer.equals("")) {
            return "Please fill required fields";
        }
        rawUser.setSecurityQ(securityQuestion);
        rawUser.setSecurityA(securityAnswer);
        StrongholdCrusader.addUser(rawUser);
        return null;
    }

}
