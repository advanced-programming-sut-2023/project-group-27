package controller.controller;

import controller.view_controllers.RegisterMenuController;
import model.StrongholdCrusader;
import model.User;
import view.RegisterMenu;

import java.util.Scanner;

public class CoreRegisterMenuController {

    public User rawUser;
    private final RegisterMenu registerMenu;
    private final CoreLoginMenuController coreLoginMenuController;

    public CoreRegisterMenuController(){
        registerMenu = new RegisterMenu(new RegisterMenuController(this));
        coreLoginMenuController = new CoreLoginMenuController();
    }
    public String run(Scanner scanner){
        String registerMenuResult;
        while (true) {
            registerMenuResult = registerMenu.run(scanner);
            switch (registerMenuResult){
                case "Exit":
                    return "Exit";
                case "Enter login menu":
                    if (coreLoginMenuController.run(scanner).equals("Exit")) return "Exit";
                    break;
            }
        }
    }
    public String initializeUser(
            String username, String password, String email, String nickname,
            String slogan) {
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
        if (!email.matches("[0-9a-zA-Z_]+@[0-9a-zA-Z_]+\\.[0-9a-zA-Z_]+")) {
            return "Invalid email format";
        }
        if (StrongholdCrusader.getAllUsers().values().stream().anyMatch(user -> user.getEmail().equals(email))) {
            return "Email already exists";
        }

        rawUser = new User(username, password, 0, slogan, email, "", "");
        return "User created successfully";
    }

    public String finalizeUser(String securityQuestion, String securityAnswer) {
        if (securityQuestion.equals("") || securityAnswer.equals("")) {
            return "Please fill required fields";
        }
        rawUser.setSecurityQ(securityQuestion);
        rawUser.setSecurityA(securityAnswer);
        StrongholdCrusader.addUser(rawUser);
        return "User created successfully";
    }

}
