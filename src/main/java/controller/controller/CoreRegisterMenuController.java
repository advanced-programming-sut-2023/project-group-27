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
            return "Please fill required fields\n";
        }
        if (slogan != null && slogan.equals("")) {
            return "If you provide '-s' then you should provide some input for it\n";
        }
        if (slogan == null) {
            slogan = "";
        }
        if (!username.matches("[0-9a-zA-Z_]+")) {
            return "Invalid username format\n";
        }
        if (StrongholdCrusader.getAllUsers().containsKey(username)) {
            return "Username already exists\n";
        }
        String passwordError = Utilities.validatePassword(password);
        if (passwordError != null) {
            return passwordError;
        }
        if (Utilities.validateEmail(email) != null) {
            return Utilities.validateEmail(email);
        }
        if (StrongholdCrusader.getAllUsers().values().stream().anyMatch(user -> user.getEmail().equals(email))) {
            return "Email already exists\n";
        }

        rawUser = new User(username, password, nickname, slogan, email, "", "");
        return null;
    }

    public String finalizeUser(String securityQuestion, String securityAnswer) {
        if (securityQuestion.equals("") || securityAnswer.equals("")) {
            return "Please fill required fields\n";
        }
        rawUser.setSecurityQ(securityQuestion);
        rawUser.setSecurityA(securityAnswer);
        StrongholdCrusader.addUser(rawUser);
        return null;
    }

}
